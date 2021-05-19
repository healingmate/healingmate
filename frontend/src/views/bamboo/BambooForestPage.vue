<template>
  <div class="relative" style="height: 100%;">
    <iframe style="position: absolute; top: 0; left: 0; width: 100%; height: 100%;" src="https://socket.healingmate.kr/bamboo-forest.html" frameborder="0"></iframe>
  </div>
</template>

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
      // // 대문자로 선언하면 const 형태로 선언됨!
      // CONNECTION: null,
      // // 본인 음성을 끌지 안끌지 지정할 isMute
      // isMute: false,
    }
	},
	// computed: {},
	// watch: {},
  created() {
    // 소켓을 연결 할 webRTC 인스턴스를 만든다. 
    // this.CONNECTION = new RTCMultiConnection();
  },
	mounted() {
    // webRTC 각종 설정 및 소켓 연결(=채팅 연결)
    // this.initVoiceChating(this.CONNECTION);
  },
	// updated() {},
  destroyed() {
    // webRTC 소켓 연결 끊기
    // this.exitVoiceChating(this.CONNECTION)
  },
	// methods: {
  //   // webRTC 각종 설정 및 소켓 연결(=채팅 연결)
  //   initVoiceChating(connection) {
  //     const nickname = this.$store.state.nickname
  //     const notify = this.$q.notify

  //     // socketIo를 가지고 있는 서버 주소
  //     connection.socketURL = SOCKET_URL
  //     // self.connection.socketURL = 'https://rtcmulticonnection.herokuapp.com:443/'
      
  //     connection.socketMessageEvent = 'bamboo-forest'

  //     connection.session = {
  //         audio: true,
  //         video: false,
  //     }

  //     connection.mediaConstraints = {
  //       audio: true,
  //       video: false
  //     }

  //     connection.sdpConstraints.mandatory = {
  //       OfferToReceiveAudio: true,
  //       OfferToReceiveVideo: false
  //     }

  //     // https://www.rtcmulticonnection.org/docs/iceServers/
  //     // use your own TURN-server here!
  //     connection.iceServers = [{
  //       'urls': [
  //         'stun:stun.l.google.com:19302',
  //         'stun:stun1.l.google.com:19302',
  //         'stun:stun2.l.google.com:19302',
  //         'stun:stun.l.google.com:19302?transport=udp',
  //       ]
  //     }]

  //     connection.userid = nickname

  //   //   // 채팅에 몇명이나 허용 할것인지
  //     connection.maxParticipantsAllowed = this.roomType==='single' ? 2 : 4

  //     connection.publicRoomIdentifier = 'bamboo-forest'

  //     connection.audiosContainer = document.getElementById('audios-container')

  //     // 소켓이 연결 되면 mediaElement를 준비해서 audios-container에 달아준다.
  //     connection.onstream = function(event) {
  //       var width = parseInt(connection.audiosContainer.clientWidth / 2) - 20
  //       var mediaElement = getHTMLMediaElement(event.mediaElement, {
  //         title: event.userid,
  //         buttons: ['full-screen'],
  //         width: width,
  //         showOnMouseEnter: false,
  //         volum: event.userid === nickname ? 0 : 1
  //       })
        
  //       connection.audiosContainer.appendChild(mediaElement);

  //       if (event.userid === nickname) {
  //         mediaElement.removeChild(mediaElement.firstChild);
  //       }

  //       setTimeout(function() {
  //         mediaElement.media.play();
  //       }, 5000);

  //       mediaElement.id = event.streamid;
  //     };

  //     // 소켓 연결이 끝나면 달아줬던 element를 뗀다
  //     connection.onstreamended = function(event) {
  //       var mediaElement = document.getElementById(event.streamid);
  //       if (mediaElement) {
  //         mediaElement.parentNode.removeChild(mediaElement);
  //       }
  //     };

  //     // 새로운 참가자가 들어오면 alert해준다.
  //     connection.onNewParticipant = function(participantId, userPreferences) {
  //       if (connection.enableLogs) {
  //         notify({
  //           position: 'top',
  //           color: 'primary',
  //           message: `${participantId}님께서 입장하셨습니다.`
  //         })
  //       }
  //       connection.acceptParticipationRequest(participantId, userPreferences);
  //     };

  //     // 참가자가 어떻게 참여할지 정의함
  //     // 1. 전체 방을 뒤져서 내가 선택한 room type에 맞는 방이 있고 해당 방이 꽉 차지 않았으면 그 방에 들어가고
  //     // 2. 조건에 맞는 방이 없으면 방을 내가 오픈한다.
  //     connection.connectSocket(function(socket) {
  //       socket.emit('get-public-rooms', connection.publicRoomIdentifier, function(listOfRooms) {
  //         if (listOfRooms.length) {
  //           for (const room of listOfRooms) {
  //             console.log(room, '의 방이 존재합니다' )
  //             // TODO: 현재 선택한 대숲 모드에 맞춰서 인원에 맞는 방에 들어가야함
  //             if (!room.isRoomFull && room.maxParticipantsAllowed === connection.maxParticipantsAllowed ) {
  //               connection.join(room.sessionid)
  //               return
  //             }
  //           }
  //           // 현재 존재하는 방을 다 순회했는데 들어갈 방이 없다? 그러면 내가 새로 판다
  //           connection.open(roomid)
  //         } else {
  //           // 현재 방이 아무것도 존재하지 않으면 첫 번째 방을 판다
  //           connection.open(roomid)
  //         }
  //       })
  //     })

  //   // room id 만들기
  //   var roomid = connection.token();

  //   //   // ......................................................
  //   //   // .......................UI Code........................
  //   //   // ......................................................

  //   },
  //   // webRTC 소켓 연결 끊기
  //   exitVoiceChating(connection) {
  //     connection.attachStreams.forEach(function(localStream) {
  //         localStream.stop()
  //     })

  //     connection.closeSocket();
  //   },
  //   onLeave() {
  //     this.$q.dialog({
  //       message: '대나무숲을 나가시겠습니까?',
  //       cancel: true,
  //       html: true,
  //       persistent: true,
  //       position: 'bottom',
  //     }).onOk(() => {
  //       this.$router.push({name: 'ArticleFeedPage'})
  //     }).onCancel(() => {
  //     }).onDismiss(() => {
  //     })
  //   },
  //   // 내 마이크 끄거나 키기
  //   onMute() {

  //     if (this.isMute) {
  //       this.CONNECTION.streamEvents.selectFirst({ userid: this.$store.state.nickname }).stream.unmute();
  //     } else {
  //       this.CONNECTION.streamEvents.selectFirst({ userid: this.$store.state.nickname }).stream.mute();
  //     }

  //     this.isMute = !this.isMute
  //   }
  // },
}
</script>


<style scoped>
/* @import url("https://socket.healingmate.kr:8282/dev/getHTMLMediaElement.css"); */

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
  background-color: transparent !important;
  background-image: url('~@/assets/images/voice-chat/unmute.svg');
}

#audios-container >>> .unmute-audio{
  background-color: transparent !important;
  background-image: url('~@/assets/images/voice-chat/mute.svg');
}

#audios-container >>> .stop {
  display: none;
}


#audios-container >>> h2 {
  color: #ffffff !important;
  font-size: 16px !important;
  line-height: 1.5;
  padding: 0 4px !important;
  position: relative !important;
  width: auto !important;
  height: auto !important;
  filter: drop-shadow(1px 0px 2px black);
  z-index: 1;
}

#audios-container >>> input {
  width: 100% !important;
  margin-top: 0 !important;
  height: 3px;
}
</style>