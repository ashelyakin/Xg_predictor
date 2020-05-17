from twisted.internet import reactor
from autobahn.twisted.websocket import WebSocketServerProtocol, WebSocketServerFactory
import os

import pandas as pd
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score


def machine_learning(arr):
    df = pd.read_csv('csv_result-data.csv')

    y = df['result'].astype('int')
    X = df.drop(['id', 'result'], axis=1)
    X_train, X_valid, y_train, y_valid = train_test_split(X, y,
                                                          test_size=0.3,
                                                          random_state=30)
    decision_tree = DecisionTreeClassifier(max_depth=4, max_features=0.7)
    trained = decision_tree.fit(X_train, y_train)
    predict = trained.predict(arr)
    return [predict, accuracy_score(trained.predict(X_train), y_train)]

port = int(os.environ['PORT'])

#for localhost
#port = 28563


class ProcessClient(WebSocketServerProtocol):
    concurrentClientCount = 0

    def onConnect(self, request):
        self.concurrentClientCount += 1
        print("Client connecting: {0}".format(request.peer))
        print(str(self.concurrentClientCount) + " concurrent clients are connected")

    def onClose(self, wasClean, code, reason):
        self.concurrentClientCount -= 1
        print("WebSocket connection closed: {0}".format(reason))

    def onMessage(self, data, isBinary):
        if isBinary:
            print("Can't answer to binary message")
            return
        data = data.decode('utf8')
        print("Data received: " + data)
        try:
            words = data.split(" ")
            data_list = [float(x) for x in words]
            res = machine_learning([data_list])
            answer = str(res[0])[1] + ' ' + str(res[1])
        except Exception:
            answer = "Ошибка ввода. Проверьте данные и введите заново"
        self.sendMessage(answer.encode('utf8'), False)
        print("Answer: " + answer)


factory = WebSocketServerFactory(u"ws://0.0.0.0:"+str(port), externalPort=80)

#for localhost
#factory = WebSocketServerFactory(u"ws://0.0.0.0:"+str(port))

factory.protocol = ProcessClient
reactor.listenTCP(port, factory)
reactor.run()

#testing
#machine_learning([[41.98,26.9,15.08,7.92,0.13,0.08,1.71,1.1,30.1,41.74,-11.64,-0.36,0.1,0.14,1.21,1.68]])
#41.98 26.9 15.08 7.92 0.13 0.08 1.71 1.1 30.1 41.74 -11.64 -0.36 0.1 0.14 1.21 1.68