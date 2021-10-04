import json
import boto3
from botocore.exceptions import ClientError
from botocore.vendored import requests
import urllib3
import json

http = urllib3.PoolManager()

def lambda_handler(event, context):
    #print("Received event: " + json.dumps(event, indent=2))
    message = event['Records'][0]['Sns']['Message']
    print("From SNS: " + message)
    print(type(json.loads(message)))
    originationNumber = json.loads(message)['originationNumber']
    messageBody = json.loads(message)['messageBody']
    body = {
        "address": messageBody,
        "mobile": originationNumber
    }
    url = 'http://ec2-52-206-188-118.compute-1.amazonaws.com:8080/api/get-eduhub-details'
    #response = requests.get(url='http://ec2-52-206-188-118.compute-1.amazonaws.com:8080/api/get-eduhub-details', data=body)
    response = http.request('GET',
                        url=url,
                        body = json.dumps(body),
                        headers = {'Content-Type': 'application/json'},
                        retries = False)
    reply = response.data.decode('utf-8')
    send_messages(reply, originationNumber)
    return message

def send_messages(message, destinationNumber):
    client = boto3.client('pinpoint',region_name='us-west-2')
    #destinationNumber = '+14699263826'
    try:
        response = client.send_messages(
            ApplicationId='9647da09613347f0b68c52d33128c734',
            MessageRequest={
                'Addresses': {
                    destinationNumber: {
                        'ChannelType': 'SMS'
                    }
                },
                'MessageConfiguration': {
                    'SMSMessage': {
                        'Body': message,
                        'Keyword': 'myKeyword',
                        'MessageType': 'TRANSACTIONAL',
                        'OriginationNumber': '+18446410190',
                        'SenderId': 'MySenderID'
                    }
                }
            }
        )

    except ClientError as e:
        print(e.response['Error']['Message'])
    else:
        print("Message sent! Message ID: "
                + response['MessageResponse']['Result'][destinationNumber]['MessageId'])


