<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>마커 생성하기</title>

</head>
<body>
<button style="background: red; color: white;" id="b1">검색어 입력해서 마커찍기</button>
	<div id="map" style="width: 70%; height: 350px;"></div>
	<script type="text/javascript" src="resources/js/jquery-3.7.1.js"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ea58e2d3bb86934cd33cbd250d4656b6"></script>
	<script>
		$(function() {
			//1. jsp가 시작하자마자 ajax를 실행해서 json을 받아와서 map을 찍을까?
			$.ajax({
				url : "map1",
				success : function(json) {
					console.log(json);
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new kakao.maps.LatLng(json.lat, json.lon), // 지도의 중심좌표 //javascript 정수 -> int 실수-> float
						level : 3
					// 지도의 확대 레벨
					};

					var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

					// 마커가 표시될 위치입니다 
					var markerPosition = new kakao.maps.LatLng(json.lat,
							json.lon);

					// 마커를 생성합니다
					var marker = new kakao.maps.Marker({
						position : markerPosition
					});

					// 마커가 지도 위에 표시되도록 설정합니다
					marker.setMap(map);

					// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
					// marker.setMap(null);    
				}//success
			})//ajax

			//2. 버튼을 클리햇을 떼 ajax를 실행해서 json을 받아와서 map을 찍을까?
			$('#b1').click(function() {
				//입력
				let input = prompt('검색어를 입력하세요.(codex, park 중)');
				$.ajax({
					url : "map1",
					data : {
						location:input
					},
					success : function(json) {
						console.log(json);
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
						mapOption = {
							center : new kakao.maps.LatLng(json.lat, json.lon), // 지도의 중심좌표 //javascript 정수 -> int 실수-> float
							level : 3
						// 지도의 확대 레벨
						};

						var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

						// 마커가 표시될 위치입니다 
						var markerPosition = new kakao.maps.LatLng(json.lat,
								json.lon);

						// 마커를 생성합니다
						var marker = new kakao.maps.Marker({
							position : markerPosition
						});

						// 마커가 지도 위에 표시되도록 설정합니다
						marker.setMap(map);

						// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
						// marker.setMap(null);    
					} //success
				})	//ajax
			}) //b1
		})//$
	</script>
</body>
</html>