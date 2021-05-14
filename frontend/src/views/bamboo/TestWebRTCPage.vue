<template>
  <div class="make-center">
    <input type="text" id="room-id" value="abcdef" autocorrect=off autocapitalize=off size=20>
    <button id="open-or-join-room">Auto Open Or Join Room</button>
    <q-btn color="primary" label="떠나기" @click="onLeave" />

    <div id="room-urls" style="text-align: center;display: none;background: #F1EDED;margin: 15px -10px;border: 1px solid rgb(189, 189, 189);border-left: 0;border-right: 0;"></div>

    <div id="audios-container"></div>
  </div>
</template>

<script src="https://socket.healingmate.kr/dev/getHTMLMediaElement.js"></script>
<script>

export default {
	name: '',
	// components: {},
	// filters: {},
  // mixins: [],
	data() {
    return {
      connection: null,
    }
	},
	// computed: {},
	// watch: {},
  created() {
    this.connection = new RTCMultiConnection();
  },
	mounted() {
    const nickname = this.$store.state.nickname
    
    var connection = this.connection

    // socketIo를 가지고 있는 서버 주소
    connection.socketURL = 'https://socket.healingmate.kr/';
    // connection.socketURL = 'https://rtcmulticonnection.herokuapp.com:443/';

    connection.socketMessageEvent = 'bamboo-forest';

    connection.session = {
      audio: true,
        video: false,
        data: true,
    };

    connection.mediaConstraints = {
      audio: true,
      video: false
    };

    connection.sdpConstraints.mandatory = {
      OfferToReceiveAudio: true,
      OfferToReceiveVideo: false
    };

    // https://www.rtcmulticonnection.org/docs/iceServers/
    // use your own TURN-server here!
    connection.iceServers = [{
      'urls': [
          'stun:stun1.l.google.com:19302',
          'stun:stun2.l.google.com:19302',
        ]
    }];

    connection.userid = nickname

    // 채팅에 몇명이나 허용 할것인지
    connection.maxParticipantsAllowed = 3;
    // connection.maxParticipantsAllowed = 4;

    connection.publicRoomIdentifier = 'bamboo-healing-contents-12333'

    connection.audiosContainer = document.getElementById('audios-container');

    connection.onstream = function(event) {
      var width = parseInt(connection.audiosContainer.clientWidth / 2) - 20;
      var mediaElement = getHTMLMediaElement(event.mediaElement, {
        title: event.userid,
        buttons: ['full-screen'],
        width: width,
        showOnMouseEnter: false
      });
      connection.audiosContainer.appendChild(mediaElement);

      setTimeout(function() {
        mediaElement.media.play();
      }, 5000);

      mediaElement.id = event.streamid;
    };

    connection.onstreamended = function(event) {
      var mediaElement = document.getElementById(event.streamid);
        if (mediaElement) {
          mediaElement.parentNode.removeChild(mediaElement);
        }
    };

    function disableInputButtons() {
      document.getElementById('open-or-join-room').disabled = true;
      document.getElementById('room-id').disabled = true;
    }

    // ......................................................
    // ......................Handling Room-ID................
    // ......................................................

    // function showRoomURL(roomid) {
      //     console.log(connection, "????")
    //     var roomHashURL = '#' + roomid;
    //     var roomQueryStringURL = '?roomid=' + roomid;

    //     var html = '<h2>Unique URL for your room:</h2><br>';

    //     html += 'Hash URL: <a href="' + roomHashURL + '" target="_blank">' + roomHashURL + '</a>';
    //     html += '<br>';
    //     html += 'QueryString URL: <a href="' + roomQueryStringURL + '" target="_blank">' + roomQueryStringURL + '</a>';

    //     var roomURLsDiv = document.getElementById('room-urls');
    //     roomURLsDiv.innerHTML = html;

    //     roomURLsDiv.style.display = 'block';
    // }

    (function() {
      var params = {},
            r = /([^&=]+)=?([^&]*)/g;

        function d(s) {
          return decodeURIComponent(s.replace(/\+/g, ' '));
        }
        var match, search = window.location.search;
        while (match = r.exec(search.substring(1)))
            params[d(match[1])] = d(match[2]);
        window.params = params;
    })();

    var roomid = '';
    if (localStorage.getItem(connection.socketMessageEvent)) {
      roomid = localStorage.getItem(connection.socketMessageEvent);
    } else {
      roomid = connection.token();
    }
    document.getElementById('room-id').value = roomid;
    document.getElementById('room-id').onkeyup = function() {
      localStorage.setItem(connection.socketMessageEvent, this.value);
    };

    var hashString = location.hash.replace('#', '');
    if (hashString.length && hashString.indexOf('comment-') == 0) {
      hashString = '';
    }

    var roomid = params.roomid;
    if (!roomid && hashString.length) {
      roomid = hashString;
    }

    if (roomid && roomid.length) {
      document.getElementById('room-id').value = roomid;
      localStorage.setItem(connection.socketMessageEvent, roomid);

      // auto-join-room
      (function reCheckRoomPresence() {
        connection.checkPresence(roomid, function(isRoomExist) {
          if (isRoomExist) {
            connection.join(roomid);
                  return;
              }

              setTimeout(reCheckRoomPresence, 5000);
          });
      })();

      disableInputButtons();
    }

    // ......................................................
    // .......................UI Code........................
    // ......................................................

    document.getElementById('open-or-join-room').onclick = function() {
      connection.connectSocket(function(socket) {
        socket.emit('get-public-rooms', connection.publicRoomIdentifier, function(listOfRooms) {
          if (listOfRooms.length) {
            for (const room of listOfRooms) {
              if (room.maxParticipantsAllowed > room.participants.length) {
                connection.openOrJoin(room.sessionid)
                return
              }
            }
            // 현재 존재하는 방을 다 순회했는데 들어갈 방이 없다? 그러면 내가 새로 판다
            connection.openOrJoin(document.getElementById('room-id').value)
          } else {
            // 현재 방이 아무것도 존재하지 않으면 첫 번째 방을 판다
            connection.openOrJoin(document.getElementById('room-id').value)
          }
        });
      });
    };
  },
	// updated() {},
	methods: {
    onLeave() {
      this.connection.closeSocket();
      this.$router.push({name: 'ArticleFeedPage'})
    },
    getUUID() {
      return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
      })
    },
  },
}
</script>


<style scoped>
@import url("https://socket.healingmate.kr/dev/getHTMLMediaElement.css");

#audios-container >>> audio{
  display: none;
}

#audios-container >>> .media-container{
  border: none;
  width: 100% !important;
  display: flex;
  justify-content: flex-end;
}

#audios-container >>> .media-controls{
  margin: 0 !important;
  position: relative;
  display: flex;
  align-items: center;
}

#audios-container >>> .media-box{
  display: flex;
  align-items: center;
  background-color: transparent;
  border: none;
  margin: 0;
  height: auto !important;
}

#audios-container >>> .volume-slider{
  display: flex;
  align-items: center;
}

#audios-container >>> .mute-audio{
  background-color: transparent;
  background-image: url('~@/assets/images/voice-chat/unmute.svg');
}

#audios-container >>> .unmute-audio{
  background-color: transparent;
  background-image: url('~@/assets/images/voice-chat/mute.svg');
}


#audios-container >>> .stop {
  display: none;
}


#audios-container >>> h2 {
  font-size: 16px !important;
  line-height: 1.5;
  padding: 0 4px !important;
  position: relative !important;
  width: auto !important;
  height: auto !important;
}

#audios-container >>> input {
  width: 100% !important;
  margin-top: 0 !important;
  height: 3px;
}
</style>