<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String ctxPath = request.getContextPath();
    //     /myspring
%>

<jsp:include page="../../header/header2.jsp" />

<style type="text/css">
	.highcharts-figure,
	.highcharts-data-table table {
	    min-width: 320px;
	    max-width: 800px;
	    margin: 1em auto;
	}
	
	div#chart_container {
	    height: 400px;
	}
	
	.highcharts-data-table table {
	    font-family: Verdana, sans-serif;
	    border-collapse: collapse;
	    border: 1px solid #ebebeb;
	    margin: 10px auto;
	    text-align: center;
	    width: 100%;
	    max-width: 500px;
	}
	
	.highcharts-data-table caption {
	    padding: 1em 0;
	    font-size: 1.2em;
	    color: #555;
	}
	
	.highcharts-data-table th {
	    font-weight: 600;
	    padding: 0.5em;
	}
	
	.highcharts-data-table td,
	.highcharts-data-table th,
	.highcharts-data-table caption {
	    padding: 0.5em;
	}
	
	.highcharts-data-table thead tr,
	.highcharts-data-table tr:nth-child(even) {
	    background: #f8f8f8;
	}
	
	.highcharts-data-table tr:hover {
	    background: #f1f7ff;
	}
	
	input[type="number"] {
	    min-width: 50px;
	}
	
	div#table_container table {width: 100%}
	div#table_container th, div#table_container td {border: solid 1px gray; text-align: center;} 
	div#table_container th {background-color: #595959; color: white;} 
</style>

<script src="<%= ctxPath%>/Highcharts-10.3.1/code/highcharts.js"></script>
<script src="<%= ctxPath%>/Highcharts-10.3.1/code/modules/exporting.js"></script>
<script src="<%= ctxPath%>/Highcharts-10.3.1/code/modules/export-data.js"></script>
<script src="<%= ctxPath%>/Highcharts-10.3.1/code/modules/accessibility.js"></script> 

<script src="<%= ctxPath%>/Highcharts-10.3.1/code/modules/series-label.js"></script>

<script src="<%= ctxPath%>/Highcharts-10.3.1/code/modules/data.js"></script>
<script src="<%= ctxPath%>/Highcharts-10.3.1/code/modules/drilldown.js"></script>


<div style="display: flex;">	
  <div style="width: 80%; min-height: 1100px; margin:auto; ">

	<h2 style="margin: 50px 0;">HR 사원 통계정보(차트)</h2>
	
	<form name="searchFrm" style="margin: 20px 0 50px 0; ">
		<select name="searchType" id="searchType" style="height: 30px;">
			<option value="">통계선택하세요</option>
			<option value="deptname">부서별 인원통계</option>
			<option value="gender">성별 인원통계</option>
			<option value="genderHireYear">성별 입사년도별 통계</option>
			<option value="deptnameGender">부서별 성별 인원통계</option>
			<option value="pageurlUsername">페이지별 사용자별 접속통계</option>
		</select>
	</form>
	
	<div id="chart_container"></div>
	<div id="table_container" style="margin: 40px 0 0 0;"></div>

  </div>
</div>

<script type="text/javascript">
   $(document).ready(function(){
	   
	   $("select#searchType").change(function(e){
		   func_choice($(e.target).val());
		   // $(e.target).val() 은 
		   // "" 또는 "deptname" 또는 "gender" 또는 "genderHireYear" 또는 "deptnameGender" 또는 "pageurlUsername" 이다.   
	   });
	   
	   // 문서가 로드 되어지면 "부서별 인원통계" 페이지가 보이도록 한다.
	   $("select#searchType").val("deptname").trigger("change");
	   
   });// end of $(document).ready(function(){})---------------
   
   
   // Function Declaration
   function func_choice(searchTypeVal) {
	   
	   switch(searchTypeVal){
	   	   case "":    // 통계선택하세요 를 선택한 경우 
		        $("div#chart_container").empty();
		        $("div#table_container").empty();
		        $("div.highcharts-data-table").empty();
		        		        
	   	        break;
	   	   
	   	   case "deptname":  // 부서별 인원통계 를 선택한 경우 (pie 차트)
			    
	   	        $.ajax({
	   	    	    url:"<%= ctxPath%>/emp/chart/employeeCntByDeptname",
	   	    	    dataType:"json",
	   	    	    success:function(json){
	   	    	    	console.log(JSON.stringify(json));
	   	    	    	/*
	   	    	    	   [{"department_name":"Shipping","percentage":"40.54","cnt":"45"}
	   	    	    	   ,{"department_name":"Sales","percentage":"30.63","cnt":"34"}
	   	    	    	   ,{"department_name":"IT","percentage":"8.11","cnt":"9"}
	   	    	    	   ,{"department_name":"Finance","percentage":"5.41","cnt":"6"}
	   	    	    	   ,{"department_name":"Purchasing","percentage":"5.41","cnt":"6"}
	   	    	    	   ,{"department_name":"Executive","percentage":"2.7","cnt":"3"}
	   	    	    	   ,{"department_name":"Accounting","percentage":"1.8","cnt":"2"}
	   	    	    	   ,{"department_name":"Marketing","percentage":"1.8","cnt":"2"}
	   	    	    	   ,{"department_name":"Administration","percentage":"0.9","cnt":"1"}
	   	    	    	   ,{"department_name":"Human Resources","percentage":"0.9","cnt":"1"}
	   	    	    	   ,{"department_name":"Public Relations","percentage":"0.9","cnt":"1"}
	   	    	    	   ,{"department_name":"부서없음","percentage":"0.9","cnt":"1"}]
	   	    	    	*/
	   	    	    	
	   	    	    	$("div#chart_container").empty();
	   			        $("div#table_container").empty();
	   			        $("div.highcharts-data-table").empty();
	   	    	    	
	   	    	    	let resultArr = [];
	   	    	    	
	   	    	    	for(let i=0; i<json.length; i++){
	   	    	    		
	   	    	    		let obj;
	   	    	    		
	   	    	    		if(i==0){
	   	    	    			obj = {
		   	    	    	            name: json[i].department_name,
		   	    	    	            y: Number(json[i].percentage),
		   	    	    	            sliced: true,
		   	    	    	            selected: true
		   	    	    	          };
	   	    	    		}
	   	    	    		else {
	   	    	    			obj = {
		   	    	    	            name: json[i].department_name,
		   	    	    	            y: Number(json[i].percentage)
		   	    	    	          };
	   	    	    		}
	   	    	    		
	   	    	    		resultArr.push(obj); // 배열속에 객체를 넣기 
	   	    	    	}// end of for-------------------------------
	   	    	    	
	   	    	    	///////////////////////////////////////////////////////////////
	   	    	    	Highcharts.chart('chart_container', {
	   	    	    	    chart: {
	   	    	    	        plotBackgroundColor: null,
	   	    	    	        plotBorderWidth: null,
	   	    	    	        plotShadow: false,
	   	    	    	        type: 'pie'
	   	    	    	    },
	   	    	    	    title: {
	   	    	    	        text: '우리회사 부서별 인원통계'
	   	    	    	    },
	   	    	    	    tooltip: {
	   	    	    	        pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
	   	    	    	    },
	   	    	    	    accessibility: {
	   	    	    	        point: {
	   	    	    	            valueSuffix: '%'
	   	    	    	        }
	   	    	    	    },
	   	    	    	    plotOptions: {
	   	    	    	        pie: {
	   	    	    	            allowPointSelect: true,
	   	    	    	            cursor: 'pointer',
	   	    	    	            dataLabels: {
	   	    	    	                enabled: true,
	   	    	    	                format: '<b>{point.name}</b>: {point.percentage:.2f} %'
	   	    	    	            }
	   	    	    	        }
	   	    	    	    },
	   	    	    	    series: [{
	   	    	    	        name: '인원비율',
	   	    	    	        colorByPoint: true,
	   	    	    	        data: resultArr 
	   	    	    	    }]
	   	    	    	});
	   	    	    	//////////////////////////////////////////////////////////////
	   	    	    	
	   	    	    	let v_html = `<table>
	   	    	    	                <tr>
	   	    	    	                  <th>부서명</th>
	   	    	    	                  <th>인원수</th>
	   	    	    	                  <th>퍼센티지</th>
	   	    	    	                </tr>`;
	   	    	    	                
	   	    	    	$.each(json, function(index, item){
	   	    	    		v_html += `<tr>
	   	    	    		              <td>\${item.department_name}</td>
	   	    	    		              <td>\${item.cnt}</td>
	   	    	    		              <td>\${item.percentage}</td>
	   	    	    		           </tr>`;
	   	    	    	});  
	   	    	    	
	   	    	    	v_html += `</table>`;
	   	    	    	
	   	    	    	$("div#table_container").html(v_html);
	   	    	    	
	   	    	    },
	   	    	    error: function(request, status, error){
					   alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					}
	   	        });
	   	   
		        break;
		   
	   	   case "gender":   // 성별 인원통계 를 선택한 경우 (pie 차트)

		   		$.ajax({
	   	    	    url:"<%= ctxPath%>/emp/chart/employeeCntByGender",
	   	    	    dataType:"json",
	   	    	    success:function(json){
	   	    	    	console.log(JSON.stringify(json));
	   	    	    	/*
	   	    	    	   [{"gender":"남","percentage":"52.3","cnt":"58"}
	   	    	    	   ,{"gender":"여","percentage":"47.7","cnt":"53"}]
	   	    	    	*/
	   	    	    	
	   	    	    	$("div#chart_container").empty();
	   			        $("div#table_container").empty();
	   			        $("div.highcharts-data-table").empty();
	   	    	    	
	   	    	    	let resultArr = [];
	   	    	    	
	   	    	    	for(let i=0; i<json.length; i++){
	   	    	    		
	   	    	    		let obj;
	   	    	    		
	   	    	    		if(i==0){
	   	    	    			obj = {
		   	    	    	            name: json[i].gender,
		   	    	    	            y: Number(json[i].percentage),
		   	    	    	            sliced: true,
		   	    	    	            selected: true
		   	    	    	          };
	   	    	    		}
	   	    	    		else {
	   	    	    			obj = {
		   	    	    	            name: json[i].gender,
		   	    	    	            y: Number(json[i].percentage)
		   	    	    	          };
	   	    	    		}
	   	    	    		
	   	    	    		resultArr.push(obj); // 배열속에 객체를 넣기 
	   	    	    	}// end of for-------------------------------
	   	    	    	
	   	    	    	///////////////////////////////////////////////////////////////
	   	    	    	Highcharts.chart('chart_container', {
	   	    	    	    chart: {
	   	    	    	        plotBackgroundColor: null,
	   	    	    	        plotBorderWidth: null,
	   	    	    	        plotShadow: false,
	   	    	    	        type: 'pie'
	   	    	    	    },
	   	    	    	    title: {
	   	    	    	        text: '우리회사 성별 인원통계'
	   	    	    	    },
	   	    	    	    tooltip: {
	   	    	    	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	   	    	    	    },
	   	    	    	    accessibility: {
	   	    	    	        point: {
	   	    	    	            valueSuffix: '%'
	   	    	    	        }
	   	    	    	    },
	   	    	    	    plotOptions: {
	   	    	    	        pie: {
	   	    	    	            allowPointSelect: true,
	   	    	    	            cursor: 'pointer',
	   	    	    	            dataLabels: {
	   	    	    	                enabled: true,
	   	    	    	                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	   	    	    	            }
	   	    	    	        }
	   	    	    	    },
	   	    	    	    series: [{
	   	    	    	        name: '인원비율',
	   	    	    	        colorByPoint: true,
	   	    	    	        data: resultArr 
	   	    	    	    }]
	   	    	    	});
	   	    	    	//////////////////////////////////////////////////////////////
	   	    	    	
	   	    	    	let v_html = `<table>
	   	    	    	                <tr>
	   	    	    	                  <th>성별</th>
	   	    	    	                  <th>인원수</th>
	   	    	    	                  <th>퍼센티지</th>
	   	    	    	                </tr>`;
	   	    	    	                
	   	    	    	$.each(json, function(index, item){
	   	    	    		v_html += `<tr>
	   	    	    		              <td>\${item.gender}</td>
	   	    	    		              <td>\${item.cnt}</td>
	   	    	    		              <td>\${item.percentage}</td>
	   	    	    		           </tr>`;
	   	    	    	});  
	   	    	    	
	   	    	    	v_html += `</table>`;
	   	    	    	
	   	    	    	$("div#table_container").html(v_html);
	   	    	    	
	   	    	    },
	   	    	    error: function(request, status, error){
					   alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					}
	   	        });
	   	   
		        break;
		   
		        
	   	   case "genderHireYear":  // 성별 입사년도별 통계 를 선택한 경우 (line 차트)
			
		   		$.ajax({
	   	    	    url:"<%= ctxPath%>/emp/chart/employeeCntByGenderHireYear",
	   	    	    dataType:"json",
	   	    	    success:function(json){
	   	    	    	console.log(JSON.stringify(json));
	   	    	    /*	
	   	    	        [{"gender":"남","Y2001":"0","Y2002":"2","Y2003":"4","Y2004":"4","Y2005":"15","Y2006":"17","Y2007":"8","Y2008":"6"}
	   	    	        ,{"gender":"여","Y2001":"1","Y2002":"5","Y2003":"2","Y2004":"6","Y2005":"14","Y2006":"7","Y2007":"11","Y2008":"5"}]
	   	    	    */
	   	    	        $("div#chart_container").empty();
			            $("div#table_container").empty();
			            $("div.highcharts-data-table").empty();
	   	    	    
	   	    	        let resultArr = [];
	   	    	    
	   	    	        for(let i=0; i<json.length; i++){
	   	    	        	
	   	    	        	let hireYear_Arr = [];
	   	    	        	hireYear_Arr.push(Number(json[i].Y2001));
	   	    	        	hireYear_Arr.push(Number(json[i].Y2002)); 
	   	    	        	hireYear_Arr.push(Number(json[i].Y2003));
	   	    	        	hireYear_Arr.push(Number(json[i].Y2004));
	   	    	        	hireYear_Arr.push(Number(json[i].Y2005));
	   	    	        	hireYear_Arr.push(Number(json[i].Y2006));
	   	    	        	hireYear_Arr.push(Number(json[i].Y2007));
	   	    	        	hireYear_Arr.push(Number(json[i].Y2008));
	   	    	        	
	   	    	        	let obj = {name: json[i].gender,
	   	    	        			   data: hireYear_Arr};
	   	    	        	
	   	    	        	resultArr.push(obj); // 배열속에 객체를 넣기
	   	    	        }// end of for-------------------------------
	   	    	    
	   	    	        ////////////////////////////////////////////////////////////
	   	    	        
						Highcharts.chart('chart_container', {
						
						    title: {
						        text: '2001년 ~ 2008년 우리회사 성별 연도별 입사인원수'
						    },
						
						    subtitle: {
						        text: 'Source: <a href="http://localhost:9090/myspring/emp/empList" target="_blank">HR.employees</a>'
						    },
						
						    yAxis: {
						        title: {
						            text: '입사 사원수'
						        }
						    },
						
						    xAxis: {
						        accessibility: {
						            rangeDescription: '범위: 2001 to 2008'
						        }
						    },
						
						    legend: {
						        layout: 'vertical',
						        align: 'right',
						        verticalAlign: 'middle'
						    },
						
						    plotOptions: {
						        series: {
						            label: {
						                connectorAllowed: false
						            },
						            pointStart: 2001
						        }
						    },
						
						    series: resultArr,
						    	
						    responsive: {
						        rules: [{
						            condition: {
						                maxWidth: 500
						            },
						            chartOptions: {
						                legend: {
						                    layout: 'horizontal',
						                    align: 'center',
						                    verticalAlign: 'bottom'
						                }
						            }
						        }]
						    }
						
						});    	        
	   	    	        
	   	    	        ////////////////////////////////////////////////////////////
	   	    	    
	   	    	        let v_html = `<table>
	   	    	                        <tr>
	   	    	                          <th>성별</th>
	   	    	                          <th>2001년</th>
	   	    	                          <th>2002년</th>
	   	    	                          <th>2003년</th>
	   	    	                          <th>2004년</th>
	   	    	                          <th>2005년</th>
	   	    	                          <th>2006년</th>
	   	    	                          <th>2007년</th>
	   	    	                          <th>2008년</th>
	   	    	                        </tr>`;
	   	    	        
	   	    	         $.each(json, function(index, item){
	   	    	        	 v_html += `<tr>
	   	    	        	               <td>\${item.gender}</td>
	   	    	        	               <td>\${item.Y2001}</td>
	   	    	        	               <td>\${item.Y2002}</td>
	   	    	        	               <td>\${item.Y2003}</td>
	   	    	        	               <td>\${item.Y2004}</td>
	   	    	        	               <td>\${item.Y2005}</td>
	   	    	        	               <td>\${item.Y2006}</td>
	   	    	        	               <td>\${item.Y2007}</td>
	   	    	        	               <td>\${item.Y2008}</td>
	   	    	        	            </tr>`;
	   	    	         });               
	   	    	                        
	   	    	         v_html += `</table>`;
	   	    	        
	   	    	         $("div#table_container").html(v_html);
	   	    	    },
	   	    	    error: function(request, status, error){
					   alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					}
	   	        });	   	   
	   	   
		        break;
		   
		        
	   	   case "deptnameGender":  // 부서별 성별 인원통계 를 선택한 경우 (Column with drilldown 차트)
			
		   		$.ajax({
	   	    	    url:"<%= ctxPath%>/emp/chart/employeeCntByDeptname",
	   	    	    dataType:"json",
	   	    	    success:function(json){
	   	    	    	console.log(JSON.stringify(json));
	   	    	    	/*
	   	    	    	   [{"department_name":"Shipping","percentage":"40.54","cnt":"45"}
	   	    	    	   ,{"department_name":"Sales","percentage":"30.63","cnt":"34"}
	   	    	    	   ,{"department_name":"IT","percentage":"8.11","cnt":"9"}
	   	    	    	   ,{"department_name":"Finance","percentage":"5.41","cnt":"6"}
	   	    	    	   ,{"department_name":"Purchasing","percentage":"5.41","cnt":"6"}
	   	    	    	   ,{"department_name":"Executive","percentage":"2.7","cnt":"3"}
	   	    	    	   ,{"department_name":"Accounting","percentage":"1.8","cnt":"2"}
	   	    	    	   ,{"department_name":"Marketing","percentage":"1.8","cnt":"2"}
	   	    	    	   ,{"department_name":"Administration","percentage":"0.9","cnt":"1"}
	   	    	    	   ,{"department_name":"Human Resources","percentage":"0.9","cnt":"1"}
	   	    	    	   ,{"department_name":"Public Relations","percentage":"0.9","cnt":"1"}
	   	    	    	   ,{"department_name":"부서없음","percentage":"0.9","cnt":"1"}]
	   	    	    	*/
	   	    	    	
	   	    	    	$("div#chart_container").empty();
			            $("div#table_container").empty();
			            $("div.highcharts-data-table").empty();
			            
			            let deptnameArr = []; // 부서명별 인원수 퍼센티지 객체 배열
			            
			            $.each(json, function(index, item){
			            	deptnameArr.push({name: item.department_name,
			                                  y: Number(item.percentage),
			                                  drilldown: item.department_name});
			            });// end of $.each(json, function(index, item){})--------
			            
			            
			            let genderArr = [];  // 특정 부서명에 근무하는 직원들의 성별 퍼센티지 객체 배열 
			            
			            $.each(json, function(index, item){
			            	$.ajax({
			            		url:"<%= ctxPath%>/emp/chart/genderCntSpecialDeptname",
				   	    	    data:{"deptname":item.department_name},
			            		dataType:"json",
				   	    	    success:function(json2){
				   	    	    	console.log(JSON.stringify(json2));
				   	    	        /*
				   	    	         [{"gender":"남","percentage":"1.8","cnt":"2"}
				   	    	         ,{"gender":"여","percentage":"0.9","cnt":"1"}]
                                     
				   	    	         [{"gender":"여","percentage":"1.8","cnt":"2"}]
                                     
				   	    	         [{"gender":"남","percentage":"5.4","cnt":"6"}
				   	    	         ,{"gender":"여","percentage":"2.7","cnt":"3"}]
				   	    	         
				   	    	         등등등 반복해서 나온다.
				   	    	        */
				   	    	        
				   	    	        let subArr = [];
				   	    	        
				   	    	        $.each(json2, function(index2, item2){
				   	    	           	
				   	    	        	subArr.push([item2.gender,
					                                 Number(item2.percentage)]);
				   	    	        	
				   	    	        });// end of $.each(json2, function(index2, item2){})-----
				   	    	        
				   	    	        
				   	    	    	genderArr.push({
						                			name: item.department_name,
						                            id: item.department_name,
						                            data: subArr 
						                           });
				   	    	        
				   	    	    },
				   	    	    error: function(request, status, error){
								   alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
								}	
			            	});
			            	
			            });// end of $.each(json, function(index, item){})----------
			            
			            
			            /////////////////////////////////////////////////////////
			            
						Highcharts.chart('chart_container', {
						    chart: {
						        type: 'column'
						    },
						    title: {
						        align: 'left',
						        text: '부서별 남녀비율'
						    },
						    subtitle: {
						        align: 'left',
						        text: 'Click the columns to view versions. Source: <a href="http://localhost:9090/myspring/emp/employeeList" target="_blank">HR.employees</a>'
						    },
						    accessibility: {
						        announceNewData: {
						            enabled: true
						        }
						    },
						    xAxis: {
						        type: 'category'
						    },
						    yAxis: {
						        title: {
						            text: '구성비율(%)'
						        }
						
						    },
						    legend: {
						        enabled: false
						    },
						    plotOptions: {
						        series: {
						            borderWidth: 0,
						            dataLabels: {
						                enabled: true,
						                format: '{point.y:.2f}%'
						            }
						        }
						    },
						
						    tooltip: {
						        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
						        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
						    },
						
						    series: [
						        {
						            name: "부서명",
						            colorByPoint: true,
						            data: deptnameArr // *** 위에서 구한 값을 대입시킴. 부서명별 인원수 퍼센티지 객체 배열 ***// 
						        }
						    ],
						    drilldown: {
						        breadcrumbs: {
						            position: {
						                align: 'right'
						            }
						        },
						        series: genderArr  // *** 위에서 구한 값을 대입시킴. 특정 부서명에 근무하는 직원들의 성별 퍼센티지 객체 배열 ***//   
						    }
						});
			            /////////////////////////////////////////////////////////
	   	    	    	
	   	    	    },
	   	    	 	error: function(request, status, error){
					   alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					}
	 	        });
	   	   
		        break;
		   
	   	   case "pageurlUsername":  // 인사관리 페이지별 사용자별 접속통계 를 선택한 경우 (Basic bar 차트) 
			
	   	        $.ajax({
	   	        	url:"<%= ctxPath%>/emp/chart/pageurlUsername",
	   	        	dataType:"json",
	   	        	success:function(json){
	   	        		let str_json = JSON.stringify(json);
	   	        	//  console.log(str_json);
	   	        	/*
	   	        		  {"categories":"[\"직원목록\",\"통계차트\"]"
	   	        		  ,"series":"[{\"name\":\"관리자\",\"data\":\"[\\\"3\\\"]\"}
	   	        		             ,{\"name\":\"서영학\",\"data\":\"[\\\"2\\\"]\"}
	   	        		             ,{\"name\":\"관리자\",\"data\":\"[\\\"2\\\"]\"}
	   	        		             ,{\"name\":\"서영학\",\"data\":\"[\\\"1\\\"]\"}]"} 
	   	        	*/
		   	        	str_json = str_json.replace(/\\/gi, "");
		    	    //  console.log(str_json);
		    		/*
		    		      {"categories":"["직원목록","통계차트"]"
		    			  ,"series":"[{"name":"관리자","data":"["3"]"}
		    			             ,{"name":"서영학","data":"["2"]"}
		    			             ,{"name":"관리자","data":"["2"]"}
		    			             ,{"name":"서영학","data":"["1"]"}]"}
	   	        	*/      	
	   	        	    str_json = str_json.replace(/\"\[/gi, "[");
			    		str_json = str_json.replace(/\]\"/gi, "]");
			    	//  console.log(str_json);
			    	/*	
	   	        	    {"categories":["직원목록","통계차트"]
			    	    ,"series":[{"name":"관리자","data":["3"]}
			    	              ,{"name":"서영학","data":["2"]}
			    	              ,{"name":"관리자","data":["2"]}
			    	              ,{"name":"서영학","data":["1"]}]}
			    	
			    	*/
	   	        	
			    	   const obj_str_json = JSON.parse(str_json);
			    	
	    		  //   console.log(obj_str_json.categories); 
	    		    /* 
	    		       ['직원목록', '통계차트']
			    	*/
			    	   
			      //   console.log(obj_str_json.series); 
	    		    /*
	    		       [{name: '관리자', data: ['3']}
                       ,{name: '서영학', data: ['2']}
                       ,{name: '관리자', data: ['2']}
                       ,{name: '서영학', data: ['1']}]
	    		    */
			    	 
	    		       const arr_series = [];
		    		
	    		       for(let i=0; i<obj_str_json.series.length; i++) {
	    		    	   if(i==0) {
	    		    	      const obj_series = {};
	    		    	   
	    		    	      obj_series.name = obj_str_json.series[i].name;
	    		    	
	    		    	      const arr_data = [];
	    		    	      arr_data.push(Number(obj_str_json.series[i].data));
	    		    	      obj_series.data = arr_data;
	    		    	   
	    		    	      arr_series.push(obj_series);
	    		    	   }
	    		    	
	    		    	   else {
	    		    	  
	    		    		  let flag = false; // obj_str_json.series 에서 name 속성값이 같은것 인지 아닌지를 판별하는 용도. 
	    		    		 
	    		    		  for(let k=0; k < arr_series.length; k++) {
	    		    		     if(arr_series[k].name == obj_str_json.series[i].name) { 
	    		    		    	// obj_str_json.series 에서 name 속성값이 같은것인 경우. 
	    		    			    
	    		    		    	arr_series[k].data.push(Number(obj_str_json.series[i].data));
	    		    			    flag = true; 
	    		    		     }
	    		    		 } // end of for-------------------	
	    		    		 
	    		    		 if(!flag) { // obj_str_json.series 에서 name 속성값이 다른 경우. 
	    		    			 const obj_series = {};
			    		    	   
			    		    	 obj_series.name = obj_str_json.series[i].name;
			    		    	
			    		    	 const arr_data = [];
			    		    	 arr_data.push(Number(obj_str_json.series[i].data));
			    		    	 obj_series.data = arr_data;
			    		    	   
			    		    	 arr_series.push(obj_series); 
	    		    		 }
	    		    	}
	    		    }// end of for--------------------------
	    		
	    		//  console.log(arr_series);
	    		    /*
	    		      [{name: '관리자', data: [3,2]}
                      ,{name: '서영학', data: [2,1]}]
	    		    */
	    		    
	    		    $("div#chart_container").empty();
					$("div#table_container").empty();
					$("div.highcharts-data-table").empty();
	    		    
	   	           ///////////////////////////////////////////////////////
	   	        	Highcharts.chart('chart_container', {
	   	        		    chart: {
	   	        		        type: 'bar'
	   	        		    },
	   	        		    title: {
	   	        		        text: '인사관리내 페이지별 접속 통계'
	   	        		    },
	   	        		    subtitle: {
	   	        		        text: 'Source: <a ' +
	   	        		            'href="https://en.wikipedia.org/wiki/List_of_continents_and_continental_subregions_by_population"' +
	   	        		            'target="_blank">Wikipedia.org</a>'
	   	        		    },
	   	        		    xAxis: {
	   	        		     // categories: ['Africa', 'America', 'Asia', 'Europe', 'Oceania'],
	   	        		     	categories: obj_str_json.categories,
	   	        		                 // obj_str_json.categories 이 ['직원목록', '통계차트'] 임. 
	   	        		     	title: {
	   	        		            text: null
	   	        		        }
	   	        		    },
	   	        		    yAxis: {
	   	        		        min: 0,
	   	        		        title: {
	   	        		            text: '접속회수 (번)',
	   	        		            align: 'high'
	   	        		        },
	   	        		        labels: {
	   	        		            overflow: 'justify'
	   	        		        }
	   	        		    },
	   	        		    tooltip: {
	   	        		        valueSuffix: ' 번접속'
	   	        		    },
	   	        		    plotOptions: {
	   	        		        bar: {
	   	        		            dataLabels: {
	   	        		                enabled: true
	   	        		            }
	   	        		        }
	   	        		    },
	   	        		    legend: {
	   	        		        layout: 'vertical',
	   	        		        align: 'right',
	   	        		        verticalAlign: 'top',
	   	        		        x: -40,
	   	        		        y: 80,
	   	        		        floating: true,
	   	        		        borderWidth: 1,
	   	        		        backgroundColor:
	   	        		            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
	   	        		        shadow: true
	   	        		    },
	   	        		    credits: {
	   	        		        enabled: false
	   	        		    },
	   	        		    series: arr_series
	   	        		    /*
	   	        		        arr_series 가
	   	        		        [{name: '관리자', data: [3,2]}
	                            ,{name: '서영학', data: [2,1]}] 임.
	   	        		    */
	   	        		});
	   	        		/////////////////////////////////////////////////////////
	   	        	},
	   	        	error: function(request, status, error){
					   alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
					}
	   	        });
	   	   
		        break;		   
	   }
	   
   }// end of function func_choice(searchTypeVal) {}-----------------
   
</script>

<jsp:include page="../../footer/footer2.jsp" />






    