import logging
import re
import sys
import time

from paramiko import AuthenticationException
from paramiko.client import SSHClient, AutoAddPolicy
from paramiko.ssh_exception import NoValidConnectionsError

paramStr = sys.argv[1]
paramList = paramStr.split(",")
file_name = paramList[0]
install_or_update = paramList[1]
device_id = paramList[2]
user_num = paramList[3]
unit_type = paramList[4]
box_type = paramList[5]
main_ip = paramList[6]
from_ip = paramList[7]
mask = paramList[8]
gateway = paramList[9]

command_list = ['cd /u01/install', 'dos2unix InstallationAtOnce.sh', 'sh InstallationAtOnce.sh', str(install_or_update), str(device_id),
                str(user_num), str(unit_type), str(box_type), str(main_ip), str(from_ip), str(mask), str(gateway)]

class SshClient():
    def __init__(self):
        self.ssh_client = SSHClient()

    def ssh_login(self, host_ip, username, password):
        try:
            self.ssh_client.set_missing_host_key_policy(AutoAddPolicy())
            self.ssh_client.connect(host_ip, port=22, username=username, password=password)
        except AuthenticationException:
            logging.warning('username or password error')
            return 1001
        except NoValidConnectionsError:
            logging.warning('connect time out')
            return 1002
        except:
            print("Unexpected error:", sys.exc_info()[0])
            return 1003
        return 1000

    def send(self):
        channel = self.ssh_client.invoke_shell()
        result = ""

        for command in command_list:
            channel.send(command + '\n')
            if "sh InstallationAtOnce.sh" in result:
                time.sleep(120)
            else:
                time.sleep(1)
            stdout = channel.recv(102400)

            print("-----------------------------------")
            result = re.split(r'\r\n', stdout.decode())
            if command == "sh InstallationAtOnce.sh":
                if len(result) >= 5:
                    if "num" in result[4]:
                        for index in range(5, len(result) - 1):
                            if file_name in result[index]:
                                if result[index].split(":")[1].strip() == file_name:
                                    channel.send(result[index].split(":")[0].strip() + '\n')
                                    channel.recv(1024)
                                    break
            print(result)
            print("-----------------------------------")

    def ssh_logout(self):
        self.ssh_client.close()


if __name__ == "__main__":
    ssh = SshClient()
    if ssh.ssh_login(host_ip="127.0.0.1", username="root", password="JSC@3passok") == 1000:
        ssh.send()
        time.sleep(600)
        ssh.ssh_logout()
