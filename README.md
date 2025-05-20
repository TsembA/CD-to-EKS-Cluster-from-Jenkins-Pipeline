# 🚀 CI/CD to Amazon EKS from Jenkins Pipeline

This project sets up a **complete CI/CD pipeline** that deploys a containerized application to an **Amazon EKS (Elastic Kubernetes Service)** cluster using **Jenkins**. It includes cluster provisioning, Jenkins configuration, AWS access, and Kubernetes deployment.

---

## 📁 Repository Structure

```bash
CD-to-EKS-Cluster-from-Jenkins-Pipeline/
├── cluster-config.yaml      # eksctl configuration for creating the EKS cluster
├── Jenkinsfile              # Jenkins pipeline to build and deploy to EKS
├── kube-config.yaml         # kubeconfig file to connect Jenkins to the EKS cluster
├── README.md                # Documentation and setup guide
```

---

## ⚖️ What the Project Does

### ✅ 1. Create EKS Cluster with `eksctl`

The cluster is provisioned with:

```bash
eksctl create cluster -f cluster-config.yaml
```

* Defines cluster name (`demo-cluster`)
* Uses `t3.medium` EC2 instances in a managed node group
* Sets desired/min/max node counts

---

### ✅ 2. Configure Jenkins with Kubernetes and AWS Access

Inside the **Jenkins Docker container**:

#### Install `kubectl`:

```bash
curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl"
chmod +x kubectl && mv kubectl /usr/local/bin/
```

#### Install `aws-iam-authenticator`:

```bash
curl -Lo aws-iam-authenticator https://github.com/kubernetes-sigs/aws-iam-authenticator/releases/download/v0.6.11/aws-iam-authenticator_0.6.11_linux_amd64
chmod +x aws-iam-authenticator && mv aws-iam-authenticator /usr/local/bin/
```

#### Add `kubeconfig` to Jenkins:

```bash
docker cp kubeconfig <jenkins_container_id>:/var/jenkins_home/.kube/config
```

---

### ✅ 3. Set AWS Credentials in Jenkins

Inside Jenkins → **Manage Credentials**:

* Add `aws_access_key_id`
* Add `aws_secret_access_key`

These are referenced in the `Jenkinsfile` using the `credentials()` function.

---

### ✅ 4. Jenkinsfile Pipeline Overview

The `Jenkinsfile` defines a three-stage pipeline:

1. **Build App**

   * Placeholder: Simulates application build
2. **Build Docker Image**

   * Placeholder: Simulates Docker image creation
3. **Deploy to EKS**

   * Uses `kubectl` to deploy and expose the application:

```groovy
sh '''
  kubectl create deployment nginx-deployment --image=nginx || true
  kubectl expose deployment nginx-deployment \
    --type=LoadBalancer \
    --name=nginx-service \
    --port=80 \
    --target-port=80 || true
'''
```

---

## 🌐 Output

After a successful run:

* Jenkins deploys the `nginx` app to the EKS cluster.
* A public LoadBalancer is created automatically by AWS.
* You can access the app via the AWS ELB hostname.

---

## 📀 Requirements

* AWS CLI, `eksctl`, and `kubectl` on the host machine
* Jenkins container with:

  * Docker access
  * `kubectl` and `aws-iam-authenticator` installed
  * Mounted `kubeconfig`
* Proper IAM roles for EKS cluster and EC2 nodes

---

## ✅ Future Enhancements

* Replace nginx with a real app (Java/Maven, etc.)
* Add Docker build and ECR push
* Use Helm or `kubectl apply` with YAML manifests
* GitHub webhooks for trigger-based deployments
* Use `docker-compose` or Jenkins shared libraries for DRY pipelines
