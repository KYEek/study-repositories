/**
 * 
 */

window.onload = function() {

    const btn_previous = document.querySelector("button#previous");
    const btn_next = document.querySelector("button#next");
    const div_images = document.querySelector("div#images");
    const images = document.querySelectorAll("div#images > img"); 

    let current_index = 0;  // 현재 인덱스 번호
    let max_indexno = images.length - 1; 
    // 3
 
	let positionValue = 0;   // div_images 위치값
	const image_width = 900; // 한번 이동시 image_width 만큼 이동한다.
	
    // ===== 다음으로 이동하는 함수 ===== //
    const func_next = function(){
        if(current_index < max_indexno) {  // 현재 인덱스 번호가 마지막(지금은 3임)이 아닌 경우 
            
            btn_previous.removeAttribute('disabled'); // 이전버튼은 활성화 상태로 만든다.
         // 또는
         // btn_previous.disabled = false; // 이전버튼은 활성화 상태로 만든다.

		    positionValue -= image_width; // images 의 위치를 왼쪽으로 이동시키도록 image_width 만큼 감소시켜 positionValue에 저장한다. 
            div_images.style.transform = `translateX(${positionValue}px)`; // x축 방향(가로방향)으로 positionValue 만큼 이동하도록 변형시킨다.
            current_index++; // 현재 인덱스 번호를 1증가 시킨다.

            document.querySelector("h2#msg").innerHTML = "";
        }
        else { // 현재 인덱스 번호가 마지막(지금은 3임)인 경우
            btn_next.setAttribute('disabled', 'true'); // 마지막 사진일 때 다음버튼을 비활성화 상태로 만든다. 
         // 또는
         // btn_next.disabled = true; // 마지막 사진일 때 다음버튼을 비활성화 상태로 만든다. 
            
            document.querySelector("h2#msg").innerHTML = "마지막 사진 입니다.";        
        }
    };


    // ===== 이전으로 이동하는 함수 ===== //
    const func_previous = function(){
        if(current_index > 0) {  // 현재 인덱스 번호가 처음이 아닌 두번째 이상인 경우 
            
            btn_next.removeAttribute('disabled'); // 다음버튼은 활성화 상태로 만든다.
         // 또는
         // btn_next.disabled = false; // 다음버튼은 활성화 상태로 만든다.

		    positionValue += image_width; // images 의 위치를 오른쪽으로 이동시키도록 image_width 만큼 증가시켜 positionValue에 저장한다. 
            div_images.style.transform = `translateX(${positionValue}px)`; // x축 방향(가로방향)으로 positionValue 만큼 이동하도록 변형시킨다. 
            current_index--; // 현재 인덱스 번호를 1감소 시킨다.

            document.querySelector("h2#msg").innerHTML = "";
        }
        else { // 현재 인덱스 번호가 처음인 경우
            btn_previous.setAttribute('disabled', 'true'); // 처음 사진일 때 이전버튼을 비활성화 상태로 만든다. 
         // 또는
         // btn_previous.disabled = true; // 처음 사진일 때 다음버튼을 비활성화 상태로 만든다. 
            
            document.querySelector("h2#msg").innerHTML = "처음 사진 입니다.";        
        }        
    };

    btn_previous.setAttribute('disabled', 'true'); // 이전 버튼은 초기화로 사용하지 못하도록 disabled 로 만든다.

    btn_previous.addEventListener("click", func_previous);  // 이전버튼 클릭시 이전으로 이동하는 함수를 호출한다. 
    btn_next.addEventListener("click", func_next);  // 다음버튼 클릭시 다음으로 이동하는 함수를 호출한다. 

}// end of window.onload = function() {}-------------------------
