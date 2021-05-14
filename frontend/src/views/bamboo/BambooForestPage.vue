<template>
  <div>
    <div class="row justify-end">
      <q-btn flat round color="healing-navy" icon="logout" @click="onLeave"/>
    </div>

    <div id="audios-container"></div>
  </div>
</template>

<script src="https://socket.healingmate.kr/dev/getHTMLMediaElement.js"></script>
<script>

export default {
	name: 'BambooForestPage',
	// components: {},
	// filters: {},
  // mixins: [],
  props: {
    roomType: String
  },
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
    const notify = this.$q.notify
    
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
    connection.maxParticipantsAllowed = this.roomType==='single' ? 2 : 4;

    connection.publicRoomIdentifier = 'bamboo-forest'

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

    connection.onNewParticipant = function(participantId, userPreferences) {
      if (connection.enableLogs) {
        notify({
          position: 'top',
          color: 'primary',
          message: `${participantId}님께서 입장하셨습니다.`
        })
      }
      connection.acceptParticipationRequest(participantId, userPreferences);
    };

    connection.connectSocket(function(socket) {
      socket.emit('get-public-rooms', connection.publicRoomIdentifier, function(listOfRooms) {
        if (listOfRooms.length) {
          for (const room of listOfRooms) {
            console.log(room, '의 방이 존재합니다' )
            if (!room.isRoomFull) {
              connection.openOrJoin(room.sessionid)
              return
            }
          }
          // 현재 존재하는 방을 다 순회했는데 들어갈 방이 없다? 그러면 내가 새로 판다
          connection.openOrJoin(roomid)
        } else {
          // 현재 방이 아무것도 존재하지 않으면 첫 번째 방을 판다
          connection.openOrJoin(roomid)
        }
      })
    })

    // ......................................................
    // ......................Handling Room-ID................
    // ......................................................

    var roomid = connection.token();

    // ......................................................
    // .......................UI Code........................
    // ......................................................

  },
	// updated() {},
	methods: {
    onLeave() {
      this.$q.dialog({
        message: '대나무숲을 나가시겠습니까?',
        cancel: true,
        html: true,
        persistent: true,
        position: 'bottom',
      }).onOk(() => {
        this.connection.close();
        this.connection.closeSocket();
        this.$router.push({name: 'ArticleFeedPage'})
      }).onCancel(() => {
      }).onDismiss(() => {
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
  display: none;
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
  color: #000000 !important;
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