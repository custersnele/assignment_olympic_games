echo Enter port number:
read PORT
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 1: CREATE RACE\n'
RACEID=$(curl -d '{"discipline": "SPRINT_100M","dateTime": "2024-06-03 15:00:00"}' -H Content-Type:application/json --no-progress-meter -X POST http://localhost:$PORT/olympicgames/races)
echo "Race $RACEID created..."
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 2: RETRIEVE RACE STATUS\n'
curl -H Content-Type:application/json --no-progress-meter -X GET http://localhost:$PORT/olympicgames/races/$RACEID/status
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 3: UPLOAD ATHLETES\n'
curl -H Content-Type:multipart/form-data -F file=@participants/participants_sprint.csv --no-progress-meter -X POST http://localhost:$PORT/olympicgames/races/$RACEID/upload
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 4: RETRIEVE RACE STATUS\n'
curl -H Content-Type:application/json --no-progress-meter -X GET http://localhost:$PORT/olympicgames/races/$RACEID/status
echo $'\nSLEEP 30 seconds'
sleep 30s
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 5: RETRIEVE RACE STATUS\n'
curl -H Content-Type:application/json --no-progress-meter -X GET http://localhost:$PORT/olympicgames/races/$RACEID/status
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 6: DOWNLOAD CSV\n'
curl -H Content-Type:application/json --no-progress-meter -X GET http://localhost:$PORT/olympicgames/races/$RACEID/csv/download
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 7: RACE RESULTS\n'
curl -d '{"status": "QUALIFIED","time": "00:00:09.800"}' -H Content-Type:application/json --no-progress-meter -X POST http://localhost:$PORT/olympicgames/races/$RACEID/1
curl -d '{"status": "QUALIFIED","time": "00:00:09.890"}' -H Content-Type:application/json --no-progress-meter -X POST http://localhost:$PORT/olympicgames/races/$RACEID/3
curl -d '{"status": "DID_NOT_FINISH"}' -H Content-Type:application/json --no-progress-meter -X POST http://localhost:$PORT/olympicgames/races/$RACEID/2
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
echo $'CONTROLE 8: SERVLET OUTPUT RACE $RACEID\n'
curl -H Content-Type:application/html --no-progress-meter -X GET http://localhost:$PORT/RaceResults?race=$RACEID
echo $'\n+++++++++++++++++++++++++++++++++++++++++++++++++++\n'
read -p "Press any key to continue..."
