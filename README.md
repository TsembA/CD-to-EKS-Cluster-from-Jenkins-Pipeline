# Create k8s EKS cluster by using cluster-config.yaml file
```bash
eksctl create cluster -f cluster-config.yaml

```
# Install kubectl inside Jenkins container
```bash
curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl; chmod +x ./kubectl; mv ./kubectl /usr/local/bin/kubectl
```
# Install aws-iam-authenticator on Jenkins server
```bash
curl -Lo aws-iam-authenticator https://github.com/kubernetes-sigs/aws-iam-authenticator/releases/download/v0.6.11/aws-iam-authenticator_0.6.11_linux_amd64
chmod +x ./aws-iam-authenticator
mv ./aws-iam-authenticator /usr/local/bin
```

# kube-config.yaml should be created on the server and copied inside the Jenkins docker container in /var/jenkins_home/.kube/<config> folder
``` bash
docker cp config <container_id>:/var/jenkins_home/.kube/<kube-config-file>
```
# Create credentials for Jenkins to get access to AWS resources
``` bash
AWS_SECRET_KEY= 1a2b3c4d5e6f7g8h9i
AWS_SECRET_ACCESS_KEY= qwe123rty456uio789***
```
# 