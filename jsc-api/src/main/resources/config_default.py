import telnetlib
import time
import sys

paramStr = sys.argv[1]
paramList = paramStr.split(",")

ip = paramList[0]
username = paramList[1]
password = paramList[2]

tn = telnetlib.Telnet(ip, 23, timeout=10)
tn.set_debuglevel(0)
tn.read_until(b'login: ', timeout=10)
tn.write(username.encode('ascii') + b'\n')

tn.read_until(b'Password: ', timeout=10)
tn.write(password.encode('ascii') + b'\n')

time.sleep(3)

cmd_result = tn.read_very_eager().decode('ascii')
if 'Login incorrect' not in cmd_result:
    pass
else:
    print("error")

w_flag = True
for index in range(len(paramList)):
    if index > 2:
        tn.write(paramList[index].encode('ascii') + b'\n')
        time.sleep(2)
        w_flag = True

        while w_flag:
            cmd_result = tn.read_very_eager().decode('ascii')
            if '#' in cmd_result:
                w_flag = False
            if "Please input your password" in cmd_result:
                w_flag = False
            if "Ascertain execute this command" in cmd_result:
                w_flag = False

if 'OPP_SUCCESS' in cmd_result:
    print('success')
else:
    print('error')
