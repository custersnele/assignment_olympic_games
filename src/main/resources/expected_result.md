Enter port number:<br>
8082<br>
+++++++++++++++++++++++++++++++++++++++++++++++++++<br>
CONTROLE 1: CREATE RACE<br>
Race 2 created...<br>
+++++++++++++++++++++++++++++++++++++++++++++++++++<br>
CONTROLE 2: RETRIEVE RACE STATUS<br>
{"id":2,"dateTime":"2024-06-03T15:00:00","discipline":"SPRINT_100M","status":"CREATED","statusDateTime":"2024-05-20T10:52:33.69422","description":null}<br>
+++++++++++++++++++++++++++++++++++++++++++++++++++<br>
CONTROLE 3: UPLOAD ATHLETES<br>
File upload started...<br>
+++++++++++++++++++++++++++++++++++++++++++++++++++<br>
CONTROLE 4: RETRIEVE RACE STATUS<br>
{"id":2,"dateTime":"2024-06-03T15:00:00","discipline":"SPRINT_100M","status":"UPLOADING_ATHLETES","statusDateTime":"2024-05-20T10:52:33.956737","description":null}<br>
SLEEP 30 seconds<br>
+++++++++++++++++++++++++++++++++++++++++++++++++++<br>
CONTROLE 5: RETRIEVE RACE STATUS<br>
{"id":2,"dateTime":"2024-06-03T15:00:00","discipline":"SPRINT_100M","status":"UPLOAD_OK","statusDateTime":"2024-05-20T10:52:44.003044","description":"9 participating in race."}<br>
+++++++++++++++++++++++++++++++++++++++++++++++++++<br>
CONTROLE 6: DOWNLOAD CSV<br>
2;100<br>
1;Norris;Turner;VU<br>
2;Ole;Klein;VG<br>
3;Amina;Stoltenberg;LY<br>
4;Toni;Douglas;HR<br>
5;Einar;Hauck;GW<br>
6;Darron;Fahey;SZ<br>
7;Hollis;Koelpin;BN<br>
8;Bernita;Hane;TJ<br>
9;Burley;Conn;EE<br>
+++++++++++++++++++++++++++++++++++++++++++++++++++<br>
CONTROLE 7: REGISTER RACE RESULTS<br>
+++++++++++++++++++++++++++++++++++++++++++++++++++<br>
CONTROLE 8: SERVLET OUTPUT RACE 2<br>
<html>
<body>
<h3>SPRINT_100M 2024-06-03 15:00</h3>
<table>
<tr><td>N. Turner</td><td>VU</td><td>00:00:09.800</td></tr>
<tr><td>A. Stoltenberg</td><td>LY</td><td>00:00:09.890</td></tr>
<tr><td>O. Klein</td><td>VG</td><td>DID_NOT_FINISH</td></tr>
<tr><td>T. Douglas</td><td>HR</td><td>ENROLLED</td></tr>
<tr><td>E. Hauck</td><td>GW</td><td>ENROLLED</td></tr>
<tr><td>D. Fahey</td><td>SZ</td><td>ENROLLED</td></tr>
<tr><td>H. Koelpin</td><td>BN</td><td>ENROLLED</td></tr>
<tr><td>B. Hane</td><td>TJ</td><td>ENROLLED</td></tr>
<tr><td>B. Conn</td><td>EE</td><td>ENROLLED</td></tr>
</table>
</body>
</html>
+++++++++++++++++++++++++++++++++++++++++++++++++++<br>
Press any key to continue...<br>
