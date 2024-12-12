/**
 * 
 */

window.onload = function() {

    const btn_previous = document.querySelector("button#previous");
    const btn_next = document.querySelector("button#next");

    const images = document.querySelectorAll("div#images > img"); 
    images.forEach( elmt => { elmt.style.display = "none"; } );
    // 초기 설정으로 모든 이미지들은 안보이게 만든다.

    images[0].style.display = ""; // 초기 설정으로 첫번째 이미지만 보이게 만든다. 

    let current_index = 0;  // 현재 인덱스 번호

    let max_indexno = images.length - 1; 
    // 3

    // ===== 다음으로 이동하는 함수 ===== //
    const func_next = function(){
        if(current_index < max_indexno) {  // 현재 인덱스 번호가 마지막(지금은 3임)이 아닌 경우 
            
            btn_previous.removeAttribute('disabled'); // 이전버튼은 활성화 상태로 만든다.
         // 또는
         // btn_previous.disabled = false; // 이전버튼은 활성화 상태로 만든다.

            images.forEach( elmt => { elmt.style.display = "none"; } );
            // 모든 이미지들은 안보이게 만든다.
            
            const img = images[++current_index];
            img.style.display = ""; // 다음번 이미지만 보이게 만든다.
            
            ////////////////////////////////////////////////////////////////
            // ====== 이미지에 애니메이션 효과 주기(transition) 시작 =====//
            img.style.opacity = "0.3";

            let opc = img.style.opacity;
        //  console.log(`opc : ${opc}`);
            // opc : 0.3

            let increase = 0.1;
            const func_img_opacity = setInterval(function(){
                increase += 0.004;
                img.style.opacity = `${Number(opc) + increase}`;

            //  console.log("img.style.opacity : ", img.style.opacity);

                if( Number(img.style.opacity) >= 1.0 ) {
            //      console.log("그만!!");
                    clearInterval(func_img_opacity);
                }

            }, 10); 
            // ====== 이미지에 애니메이션 효과 주기(transition) 끝 =====//
            ////////////////////////////////////////////////////////////////

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

            images.forEach( elmt => { elmt.style.display = "none"; } );
            // 모든 이미지들은 안보이게 만든다.
            
            const img = images[--current_index];
            img.style.display = ""; // 이전 이미지만 보이게 만든다.
            
            ////////////////////////////////////////////////////////////////
            // ====== 이미지에 애니메이션 효과 주기(transition) 시작 =====//
            img.style.opacity = "0.3";

            let opc = img.style.opacity;
        //  console.log(`opc : ${opc}`);
            // opc : 0.3

            let increase = 0.1;
            const func_img_opacity = setInterval(function(){
                increase += 0.004;
                img.style.opacity = `${Number(opc) + increase}`;

            //  console.log("img.style.opacity : ", img.style.opacity);

                if( Number(img.style.opacity) >= 1.0 ) {
            //      console.log("그만!!");
                    clearInterval(func_img_opacity);
                }

            }, 10); 
            // ====== 이미지에 애니메이션 효과 주기(transition) 끝 =====//
            ////////////////////////////////////////////////////////////////

            document.querySelector("h2#msg").innerHTML = "";
        }
        else { // 현재 인덱스 번호가 처음인 경우
            btn_previous.setAttribute('disabled', 'true'); // 처음 사진일 때 이전버튼을 비활성화 상태로 만든다. 
         // 또는
         // btn_previous.disabled = true; // 처음 사진일 때 이전버튼을 비활성화 상태로 만든다. 
            
            document.querySelector("h2#msg").innerHTML = "처음 사진 입니다.";        
        }
    };

    btn_previous.setAttribute('disabled', 'true'); // 이전 버튼은 초기화로 사용하지 못하도록 disabled 로 만든다.

    btn_previous.addEventListener("click", func_previous);  // 이전버튼 클릭시 이전으로 이동하는 함수를 호출한다. 
    btn_next.addEventListener("click", func_next);  // 다음버튼 클릭시 다음으로 이동하는 함수를 호출한다. 

}// end of window.onload = function() {}-------------------------
