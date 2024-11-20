
$(document).ready(function(){
	
	const arr_btn = [{color:"red", hangul:"빨강", music:"맘마미아4-dancing_queen.mp3"}
					,{color:"orange", hangul:"주황", music:"Ob-La-Di Ob-La-Da.ogg"}
					,{color:"yellow", hangul:"노랑", music:"Ending.wav"}
					,{color:"green", hangul:"초록", music:"Rain & Tears - Aprodite's Child.mp3"}
					,{color:"blue", hangul:"파랑", music:"Sailing - Rod Stewart.mp3"}
					,{color:"navy", hangul:"남색", music:"Piano Man - Billy Joel.mp3"}
					,{color:"purple", hangul:"보라", music:"The Sound Of Silence - Simon & Garfunkel.mp3"}
				];
	
	let html = `<span id="msg">버튼을 눌러보세요</span>`;
	
	arr_btn.forEach( item => {
		html += `<button type='button'>${item.color}</button>`;
	} );

	html += `<span class='myaudio' id='audio_pause_play'>오디오일시정지</span>`;
	html += `<span class='myaudio' id='audio_load'>오디오처음부터듣기</span>`;
				   
	$("div#container > div#btnsDiv").html(html);

	let audio;

	$(document).on("click", "div#container > div#btnsDiv > button", e => {
		const i = $("div#container > div#btnsDiv > button").index($(e.target)); 
        //  선택자.index(특정엘리먼트); ==> 선택자가 복수개 엘리먼트를 가리키는 것일때 특정엘리먼트가 복수개 엘리먼트중 몇 번째 index 값을 가지는지를 알려주는 것이다. 

		const color = arr_btn[i].color;
		const hangul = arr_btn[i].hangul;

		$("div#container > div:nth-child(2) > div#colorDiv").css({'width': '200px',	'height': '200px', 'background-color': color});
		$("div#container > div:nth-child(2) > div#hangulDiv").html(hangul).css({'color':color}); 

		// ==== 자바스크립트에서 오디오 객체 만들어서 재생하기 ==== //
		if(audio != undefined) { // 빨강~보라 까지 버튼을 연속적으로 클릭시 오디오 음악이 동시에 나오는 것을 방지하기 위한 것임.
			audio.pause();       // 먼저, 앞서 실행중인 audio 를 일시정지함.
		}
		
		audio = new Audio(`audio/${arr_btn[i].music}`);
		audio.play(); // audio를 재생함

		$("div#music_title_view").html(`곡명 : ${arr_btn[i].music}`);
	}); 
	

    // ==== 자바스크립트에서 오디오 일시정지 및 재생하기 ==== //
	$(document).on("click", "span#audio_pause_play", e => {

		const text = $(e.target).text();

		if(text == "오디오일시정지") {
			audio.pause(); // audio를 일시정지함
			$(e.target).text("오디오재생");
		}

		else if(text == "오디오재생"){
			audio.play(); // audio를 재생함
			$(e.target).text("오디오일시정지");
		}

	});

    // ==== 자바스크립트에서 오디오 다시 로드 하기 ==== //
	$(document).on("click", "span#audio_load", () => {
		audio.load(); // audio를 다시 load 한 후에 
		audio.play(); // audio를 재생함
	});


	$("div#container > div#btnsDiv > button").eq(0).trigger('click');

});// end of $(document).ready(function(){})------------------