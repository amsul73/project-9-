from flask import Flask, jsonify, request, make_response
import json
import base64
import hashlib
import string

app = Flask(__name__)

database = {
    "admin":["adm1n", 0],
    "qwer1234":["qqqq", 1],
    "abcd1234":["qqqq", 2],
    "alsp":["1111", 3],
    "5un9hun":["aaaa", 4]
}

memberId = 5

@app.post('/login')
def login():
    username = request.json['username']
    password = request.json['password']
    if(username in database):
        if(database[username][0] == password):
            data = {
                "success": True,
                "message": "로그인 성공",
                "memberId": database[username][1]
            }
            resp = make_response(jsonify(data))
            resp.set_cookie('JSESSIONID', str(base64.b64encode(str(data["memberId"]).encode()).decode() + '.' + base64.b64encode(username.encode()).decode() + '.' + hashlib.sha256(username.encode()).hexdigest()).replace('=', ''))
            resp.set_cookie('memberId', str(database[username][1]))
            return resp
        else:
            data = {
                "success": False,
                "message": "아이디와 비밀번호를 다시 확인해 주세요",
                "ERRCode": "1004"
            }
    else:
        data = {
            "success": False,
            "message": "아이디와 비밀번호를 다시 확인해 주세요",
            "ERRCode": "1004"
        }
    return jsonify(data)

@app.post('/signup')
def register():
    username = request.json['username']
    password = request.json['password']
    realname = request.json['realname']
    email = request.json['email']

    data = {
        "success": False,
	    "errcode" : 1003,
        "message": ""
    }

    global memberId

    if(len(username) >= 20 or username in string.printable[62:]):
        data["message"] = "아이디는 공백이나 특수문자 없이 20자 이내로 입력하세요."
        return jsonify(data)

    if(len(realname) >= 20 or realname == ''):
        data["message"] =  "이름은 20자 이내로 입력해 주세요."
        return jsonify(data)

    if(username not in database):
        database.update({username:[password, memberId]})
        data = {
            "success": True,
            "message": "회원가입이 성공적으로 완료되었습니다.",
            "memberId": memberId
        }
        memberId += 1
    else:    
        data = {
            "success": False,
            "errcode": 1001,
            "message": "동일한 아이디의 사용자가 있습니다."
        }
    return jsonify(data)

if __name__ == '__main__':  
   app.run('0.0.0.0',port=5000,debug=False)
