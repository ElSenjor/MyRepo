'''
Created on 18.02.2018

@author: Alex-admin
'''
import requests
from json import dumps
import json
name='COTD-EN043'
url= "http://yugiohprices.com/api/price_for_print_tag/"
url = url + name
print('starting request')
request = requests.get(url)
if request.status_code != 200:
    print(request)
    print("Request status not OK. Returning None...")
prices = request.json()
prices = json.loads(dumps(prices))
print(prices['data']['price_data']['price_data']['data']['prices']['average'])