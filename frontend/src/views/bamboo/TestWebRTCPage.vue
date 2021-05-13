<template>
  <div class="make-center">
    <input type="text" id="room-id" value="abcdef" autocorrect=off autocapitalize=off size=20>
    <button id="open-room">Open Room</button>
    <button id="join-room">Join Room</button>
    <button id="open-or-join-room">Auto Open Or Join Room</button>

    <div id="room-urls" style="text-align: center;display: none;background: #F1EDED;margin: 15px -10px;border: 1px solid rgb(189, 189, 189);border-left: 0;border-right: 0;"></div>

    <div id="audios-container"></div>
  </div>
</template>

<script src="https://rtcmulticonnection.herokuapp.com/dev/getHTMLMediaElement.js"></script>
<script>

export default {
	name: '',
	// components: {},
	// filters: {},
  // mixins: [],
	// props: {},
	data() {
    return {
    }
	},
	// computed: {},
	// watch: {},
  created() {
  },
	mounted() {
    const nickname = this.$store.state.nickname
    
    var connection = new RTCMultiConnection();

    // socketIo를 가지고 있는 서버 주소
    connection.socketURL = 'https://rtcmulticonnection.herokuapp.com:443/';

    connection.socketMessageEvent = 'audio-conference-demo';

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

    connection.audiosContainer = document.getElementById('audios-container');
    connection.onstream = function(event) {
      var width = parseInt(connection.audiosContainer.clientWidth / 2) - 20;
        console.log(event, "1")
        var mediaElement = getHTMLMediaElement(event.mediaElement, {
          title: nickname,
          buttons: ['full-screen'],
          width: width,
          showOnMouseEnter: false
        });
        console.log(connection.socketURL,"?>???")
        console.log('????')
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
        document.getElementById('open-room').disabled = true;
        document.getElementById('join-room').disabled = true;
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
    document.getElementById('open-room').onclick = function() {
      disableInputButtons();
        connection.open(document.getElementById('room-id').value, function() {
          // showRoomURL(connection.sessionid);
        });
    };

    document.getElementById('join-room').onclick = function() {
      disableInputButtons();
        connection.join(document.getElementById('room-id').value);
    };

    document.getElementById('open-or-join-room').onclick = function() {
      disableInputButtons();
        connection.openOrJoin(document.getElementById('room-id').value, function(isRoomExist, roomid) {
          if (!isRoomExist) {
            // showRoomURL(roomid);
            }
        });
    };
  },
	// updated() {},
	// methods: {},
}
</script>


<style scoped>
@import url("https://rtcmulticonnection.herokuapp.com/dev/getHTMLMediaElement.css");

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