import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { AppComponent } from './app.component';
import { HearthstoneService } from './service/hearthstone.service';

export class WebSocketAPI {
    ws;
    socketUrl;
    userName: string;
    userId: string;
    opponentUserId;
    opponentUserName;
    playGameUrl;
    ourTurn: boolean;
    gameover: boolean;
    currentMessage: String;

    constructor(private hearthstoneService: HearthstoneService) { }

    connect() {
        const socket = new WebSocket('ws://localhost:8081/greeting');
        this.ws = Stomp.over(socket);
        const that = this;
        this.ws.connect({}, frame => {
            that.ws.subscribe('/errors', message => {
                alert('Error ' + message.body);
            });
            that.ws.subscribe('/topic/reply/' + this.socketUrl, message => {
                let tempData;
                let tempUserNameObject;
                if (message.body === 'start') {
                    setTimeout(() => {
                        this.hearthstoneService.getPlayer2Id(this.socketUrl).subscribe(
                            data => {
                                tempData = data;
                                this.opponentUserId = tempData.userId;
                            }, error1 => {
                                console.log(error1);
                            }, () => {
                                this.hearthstoneService.getUserName(this.opponentUserId).subscribe(data => {
                                    tempUserNameObject = data;
                                }, error => {
                                    console.error(error);
                                }, () => {
                                    this.opponentUserName = tempUserNameObject.userName;
                                });
                            }
                        )
                    }, 1500);
                } else {
                    const stringInfo = JSON.parse(message.body);
                    if (stringInfo.turnBy == 'p1') {
                        this.ourTurn = false; // TODO
                    } else if (stringInfo.turnBy == 'p2') {
                        this.ourTurn = true; // TODO
                    }
                    if (stringInfo.winningMove == 'true') {
                        this.gameover = true;
                    }
                    if (stringInfo.turnBy == 'p1') {
                        this.currentMessage = 'Congratulations, You Won! Refresh to play again.';
                    } else {
                        this.currentMessage = 'Bad Luck! ' + this.opponentUserName + ' won! Refresh to play again.';
                    }
                }
            });
        }, error => {
            this.overlayOn();

        })
    }

    disconnect() {
        if (this.ws != null) {
            this.ws.ws.close();
        }
        console.log('Disconnected');
    }

    sendName(value) {
        const data = JSON.stringify({
            name: value
        });
        this.ws.send('/app/message/' + this.socketUrl, {}, data);
        console.log('SEND-SEND ' + value);
    }


    overlayOn() {
        document.getElementById('overlay').style.display = 'block';
    }

    overlayOff() {
        document.getElementById('overlay').style.display = 'none';
    }




    /*     webSocketEndPoint: string = 'http://localhost:8081/ws';
        topic: string = "/api/hello";
        stompClient: any;
        appComponent: AppComponent;
        constructor(appComponent: AppComponent) {
            this.appComponent = appComponent;
        }
    
        _connect() {
            console.log("Initialize WebSocket Connection");
            let ws = new SockJS(this.webSocketEndPoint);
            this.stompClient = Stomp.over(ws);
            const _this = this;
            _this.stompClient.connect({}, function (frame) {
                _this.stompClient.subscribe(_this.topic, function (sdkEvent) {
                    _this.onMessageReceived(sdkEvent);
                });
                //_this.stompClient.reconnect_delay = 2000;
            }, this.errorCallBack);
        };
    
        _disconnect() {
            if (this.stompClient !== null) {
                this.stompClient.disconnect();
            }
            console.log("Disconnected");
        }
    
        // on error, schedule a reconnection attempt
        errorCallBack(error) {
            console.log("errorCallBack -> " + error)
            setTimeout(() => {
                this._connect();
            }, 5000);
        }
    
        _send(message) {
            console.log("calling logout api via web socket");
            this.stompClient.send("/api/hello", {}, JSON.stringify(message));
        }
    
        onMessageReceived(message) {
            console.log("Message Recieved from Server :: " + message);
            this.appComponent.handleMessage(JSON.stringify(message.body));
            // this.appComponent.handleMessage(message);
        } */
}