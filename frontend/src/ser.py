from flask import Flask, jsonify, request, make_response
import json

app = Flask(__name__)

@app.post('/login')
def login():
    username = request.json['username']
    password = request.json['password']
    print(username)
    print(password)
    if(username == "5un9hun"):
        data = {
            "success": True,
            "message": "로그인 성공",
            "memberId": 1
        }
    else:
        data = {
            "success": False,
            "message": "아이디와 비밀번호를 다시 확인해 주세요"
        }
    return jsonify(data)

@app.post('/register')
def register():
    username = request.json['username']
    password = request.json['password']
    pass_confirm = request.json['pass_confirm']
    realname = request.json['realname']
    email = request.json['email']
    print(username)
    print(password)
    print(pass_confirm)
    print(realname)
    print(email)
    if(username == "5un9hun"):
        data = {
            "message":"good"
        }
    else:
        data = {
            "message":"zzlol"
        }
    return data

if __name__ == '__main__':  
   app.run('0.0.0.0',port=5000,debug=False)
