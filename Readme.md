## INSTALL JAVA 

- sudo apt install openjdk-17-jdk

## INSTALL MAVEN 

- sudo apt install maven

## RUN APP

- mvn spring-boot:run

## DOCKER INSTALLATION
### **Simple Docker Installation Guide for Ubuntu**


### **Step 1: Update Your System**
Ensure your system is up-to-date:
```bash
sudo apt update
sudo apt upgrade -y
```

---

### **Step 2: Install Required Dependencies**
Install necessary packages to enable `apt` to use HTTPS for repositories:
```bash
sudo apt install apt-transport-https ca-certificates curl software-properties-common -y
```

---

### **Step 3: Add Docker’s Official GPG Key**
Add Docker's GPG key to verify the authenticity of packages:
```bash
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
```

---

### **Step 4: Add Docker Repository**
Add the Docker repository to your system:
```bash
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```

---

### **Step 5: Install Docker**
Update the package index and install Docker:
```bash
sudo apt update
sudo apt install docker-ce docker-ce-cli containerd.io -y
```

---

### **Step 6: Verify Installation**
Check if Docker is installed and running:
```bash
docker --version
sudo systemctl status docker
```

---

### **Step 7: Run Docker Without `sudo` (Optional)**
To run Docker commands as a non-root user, add your user to the `docker` group:
```bash
sudo usermod -aG docker $USER
```
Log out and log back in for the changes to take effect.

---

### **Step 8: Test Docker Installation**
Run a test container to verify everything works:
```bash
docker run hello-world
```

If successful, you’ll see a message confirming that Docker is working correctly.

---

## DOCKERIZE APPLICATION

Great to hear that you have your Spring Boot application ready and Docker installed! Let's proceed to containerize your application and prepare it for deployment. Here's a step-by-step guide:

---

## 🐳 Step 1: Build Your Spring Boot Application

First, package your application into a JAR file:

```bash
mvn clean package
```

This command will generate a JAR file in the `target` directory, such as `target/demo-0.0.1-SNAPSHOT.jar`.

---

## 📦 Step 2: Create a Dockerfile

In the root directory of your project, create a file named `Dockerfile` (without any extension) and add the following content:

```dockerfile
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
```

This Dockerfile uses a lightweight Alpine Linux image with OpenJDK 17, sets the working directory, copies your JAR file into the container, exposes port 8080, and specifies the command to run your application.

---

## 🛠️ Step 3: Build the Docker Image

Build the Docker image using the following command:

```bash
docker build -t demo-app .
```

This command tags the image as `demo-app` and uses the current directory (`.`) as the build context.

---

## 🚀 Step 4: Run the Docker Container

Run your application inside a Docker container:

```bash
docker run -p 8080:8080 demo-app
```

This command maps port 8080 of the container to port 8080 on your host machine, allowing you to access the application at `http://localhost:8080`.

---

## ✅ Step 5: Test the Application

Verify that your application is running correctly by accessing it in a web browser or using `curl`:

```bash
curl http://localhost:8080/your-endpoint
```

Replace `/your-endpoint` with the actual endpoint defined in your Spring Boot application.

---

## INSTALL KUBERNETES

### ✅ Install `kubectl` via Snap

1. **Ensure Snap is installed**:

   Snap is typically pre-installed on Ubuntu 16.04 and later. To verify if Snap is installed, run:

   ```bash
   snap version
   ```

   If Snap isn't installed, you can install it using:

   ```bash
   sudo apt update
   sudo apt install snapd
   ```

2. **Install `kubectl` using Snap**:

   Once Snap is installed, you can install `kubectl` with:

   ```bash
   sudo snap install kubectl --classic
   ```

   This command installs the latest stable version of `kubectl` and grants it the necessary permissions to interact with your Kubernetes cluster. citeturn0search0

3. **Verify the installation**:

   After installation, confirm that `kubectl` is installed correctly by checking its version:

   ```bash
   kubectl version --client
   ```

   This should display the client version of `kubectl`, indicating a successful installation.


## 🧪 Step 2: Set Up a Local Kubernetes Cluster with Minikube
Minikube is a tool that allows you to run a single-node Kubernetes cluster locally, which is perfect for development and testing purpose.

### Install Minikube:

1. **Download the Minikube binary:**

   ```bash
   curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
   ```

2. **Install Minikube:**

   ```bash
   sudo install minikube-linux-amd64 /usr/local/bin/minikube
   ```

3. **Verify the installation:**

   ```bash
   minikube version
   ```

   This should display the installed version of Minikube.

---

## 🔧 Step 3: Start Minikub

Now that Minikube is installed, let's start the Kubernetes clustr:

```bash
minikube start
```

This command will download the necessary images and start a local Kubernetes clustr.

---


It seems you're encountering an issue with Minikube when attempting to start it with the Docker driver. The error message indicates that the Docker driver should not be used with root privileges. Here's how you can resolve this:

---

## 🛠️ Step 1: Add Your User to the Docker Group
Minikube requires Docker to be accessible by your user without root privilegesTo achieve this, add your user to the Docker group

```bash
sudo usermod -aG docker $USER
```
After running this command, log out and log back in, or run

```bash
newgrp docker
```
This ensures that your user has the necessary permissions to interact with Docker

---

## 🚫 Step 2: Avoid Running Minikube as Root
Minikube should not be run with root privileges when using the Docker drive. Running Minikube as root can lead to permission issues and potential security risk. Always run Minikube as a non-root use.

---

## ✅ Step 3: Start Minikube
Once you've added your user to the Docker group and ensured you're not running Minikube as root, start Minikube with the Docker drive:

```bash
minikube start --driver=docker
``

This command should initialize Minikube without the previous error.

---

If you continue to encounter issues or need further assistance, feel free to ask! 



## 📦 Step 4: Deploy Your Dockerized Spring Boot Application to Kubernets

With your local Kubernetes cluster running, you can now deploy your Dockerized Spring Boot applicaton.

1. **Create a Kubernetes Deployment:**

   Assuming your Docker image is named `demo-app`, create a deployment using the following command:

   ```bash
   kubectl create deployment demo-app --image=demo-app
   ```

2. **Expose the Deployment as a Service:**

   To access your application, expose it as a service:

   ```bash
   kubectl expose deployment demo-app --type=NodePort --port=8080
   ```

3. **Access the Application:**

   Retrieve the URL to access your application:

   ```bash
   minikube service demo-app --url
   ```

   This command will provide a URL that you can open in your browser to access your Spring Boot application running on Kubernetes.

---


## DEPLOY ON KUBERNETES

### Option 1: **Use a Local Docker Image**
You can build and use a local Docker image without needing Docker Hub. Here's how:

1. **Build the Docker Image Locally**:
   If you have a `Dockerfile` in your project directory, you can build the image locally:

   ```bash
   docker build -t demo-app .
   ```

   This command will create a local image named `demo-app`.

2. **Ensure the Image is Available Locally**:
   Check that the image exists in your local Docker registry:

   ```bash
   docker images
   ```

   You should see `demo-app` listed there.

3. **Use the Local Image in Kubernetes**:
   Update your Kubernetes YAML file (deployment configuration) to use the local image. If you’re using `kubectl`, the image name would be `demo-app`:

   ```yaml
    apiVersion: apps/v1
    kind: Deployment
    metadata:
    name: demo-app
    spec:
    replicas: 1
    selector:
        matchLabels:
        app: demo-app
    template:
        metadata:
        labels:
            app: demo-app
        spec:
        containers:
        - name: demo-app
            image: demo-app  # This should be the name of your local image or the image you're using
            ports:
            - containerPort: 8080

    ```

    4. **Apply the Configuration**:
    Apply the deployment YAML to Kubernetes:

    ```bash
    kubectl apply -f deployment.yaml
   ```

   Kubernetes will use the locally built image for the pod.

### Option 2: **Use Minikube's Built-in Docker Registry**
If you are using Minikube, it provides a built-in Docker registry that you can use to avoid pushing your images to external registries.

1. **Access Minikube's Docker Daemon**:
   Use Minikube’s Docker daemon to build the image directly within Minikube:

   ```bash
   eval $(minikube -p minikube docker-env)
   ```

   This command sets up the Docker environment to use Minikube’s Docker daemon.

2. **Build the Image in Minikube**:
   After running the above command, you can build your Docker image inside Minikube:

   ```bash
   docker build -t demo-app .
   ```

3. **Use the Image in Kubernetes**:
   Now that the image is built within Minikube’s Docker daemon, you can reference it in your Kubernetes deployment YAML file. Minikube will be able to find the image locally.

4. **Deploy the Application**:
   Apply the deployment YAML as before:

   ```bash
   kubectl apply -f deployment.yaml
   ```

## SCALE IN KUBERNETES

If you want to deploy your Docker app on Kubernetes **without using Docker Hub or any external registry**, you can use your locally built image directly within Minikube. This is the easiest approach for local development. Here's a step-by-step guide:

---

### **1. Use Local Images in Minikube**
Minikube allows you to use locally built images without needing a registry. You can load your image directly into Minikube's Docker environment.

#### **Steps to Load Local Image into Minikube**
1. **Build the Docker Image**:
   ```bash
   docker build -t demo-app:latest .
   ```
   This creates a local image named `demo-app`.

2. **Load the Image into Minikube**:
   ```bash
   minikube image load demo-app:latest
   ```
   This makes the image accessible to Kubernetes running in Minikube.

3. **Update Deployment YAML**:
   Modify your `deployment.yaml` file to use the local image and set `imagePullPolicy` to `Never`:
   ```yaml
   apiVersion: apps/v1
   kind: Deployment
   metadata:
     name: demo-app
   spec:
     replicas: 1
     selector:
       matchLabels:
         app: demo-app
     template:
       metadata:
         labels:
           app: demo-app
       spec:
         containers:
         - name: demo-app
           image: demo-app:latest  # Use the local image name
           imagePullPolicy: Never  # Prevent Kubernetes from trying to pull the image from a registry
           ports:
           - containerPort: 8080
   ```

4. **Apply the Deployment**:
   ```bash
   kubectl apply -f deployment.yaml
   ```

---

### **2. Expose the Application**
Create a service to expose your application.

#### **Service YAML**
```yaml
apiVersion: v1
kind: Service
metadata:
  name: demo-app-service
spec:
  selector:
    app: demo-app  # Match labels from deployment
  ports:
    - protocol: TCP
      port: 80       # External port for accessing the service
      targetPort: 8080  # Port inside the container
  type: NodePort     # Expose service on a node port for local access
```

Apply the service configuration:
```bash
kubectl apply -f service.yaml
```

---

### **3. Access Your Application**
To access your app locally:

- Use Minikube's built-in service URL:
  ```bash
  minikube service demo-app-service --url
  ```
- Alternatively, find the NodePort and access it via `http://:`.

---

### **4. Scale Your Deployment**
You can scale your application by increasing the number of replicas.

#### Scale Command:
```bash
kubectl scale deployment/demo-app --replicas=3
```

---

### **Important Notes**
- Always set `imagePullPolicy` to `Never` when using local images, as Kubernetes will otherwise try to pull it from Docker Hub.
- Ensure that Minikube's Docker daemon has access to your locally built images using `minikube image load`.

---

### **Advantages of This Approach**
- No need for external registries like Docker Hub.
- Fast and simple setup for local development.
- Easy scaling and testing within Minikube.


### AUTOSCALING

- kubectl autoscale deployment demo-app --cpu-percent=50 --min=1 --max=10


### RE-DEPLOYMENT

If you have changes in your app and want to deploy the updated version in Kubernetes, follow these steps:

---

### **1. Build and Push the Updated Docker Image**
If you made changes to your application, first rebuild the Docker image with a new tag (e.g., `v2`) to differentiate it from the previous version.

```bash
# Build the updated Docker image
docker build -t demo-app:v2 .

# Load it into Minikube (if using Minikube)
minikube image load demo-app:v2

# Or push it to a registry (if using a registry like Docker Hub)
docker tag demo-app:v2 /demo-app:v2
docker push /demo-app:v2
```

---

### **2. Update the Deployment**
You need to update your deployment to use the new image.

#### **Option 1: Update Deployment YAML**
Edit your `deployment.yaml` file and update the `image` field for your container:
```yaml
containers:
  - name: demo-app
    image: demo-app:v2  # Update to the new image version
```

Apply the updated deployment:
```bash
kubectl apply -f deployment.yaml
```

Kubernetes will perform a **rolling update**, gradually replacing old pods with new ones running the updated version.

---

#### **Option 2: Use `kubectl set image`**
You can directly update the image of your deployment without modifying the YAML file:
```bash
kubectl set image deployment/demo-app demo-app=demo-app:v2
```

This command updates the container `demo-app` in the deployment `demo-app` to use the new image `demo-app:v2`.

---

### **3. Verify the Update**
Check the status of your deployment and pods to ensure they are updated successfully:
```bash
# Check deployment rollout status
kubectl rollout status deployment/demo-app

# Check pods to confirm they are running the new version
kubectl get pods -l app=demo-app

# Describe one of the pods to verify the image version
kubectl describe pod 
```

---

### **4. Rollback if Needed**
If something goes wrong with the new version, you can roll back to the previous stable version:
```bash
kubectl rollout undo deployment/demo-app
```
This will restore your app to its previous state.

---

### **5. Automate Updates (Optional)**
For automated deployments, consider adding a unique tag or timestamp as part of your CI/CD pipeline. For example:
- Use `DEPLOY_TS` as an environment variable with a timestamp.
- Automate updates with tools like ArgoCD or Jenkins.

---

### **Best Practices for Updating Your App**
1. **Use Rolling Updates**: This ensures no downtime by gradually replacing old pods with new ones.
2. **Test Locally First**: Test your changes in a local environment (e.g., Minikube) before deploying them to production.
3. **Tag Your Images**: Always use unique tags (`v1`, `v2`, etc.) for each version of your app.
4. **Monitor Rollouts**: Use `kubectl rollout status` and logs to monitor updates for errors.


### ADVANTAGES OF SCALING

### **Advantages of Scaling in Kubernetes**

Scaling your application in Kubernetes provides several key benefits:

1. **Improved Performance and Availability**:
   - By increasing the number of replicas (pods), your application can handle more traffic, ensuring stable performance during high-demand periods[2][3][5].
   - Replicas ensure redundancy, reducing the risk of downtime if one pod fails.

2. **Load Balancing**:
   - Traffic is automatically distributed across all running pods, preventing any single pod from being overwhelmed[3][6].

3. **Resource Optimization**:
   - Kubernetes ensures efficient use of resources by scaling up during peak demand and scaling down during idle times, saving costs[4][5].

4. **Fault Tolerance**:
   - If a pod crashes or a node fails, Kubernetes automatically reschedules or replaces the failed pods to maintain availability[2][6].

5. **Flexibility with Autoscaling**:
   - Kubernetes supports Horizontal Pod Autoscaling (HPA), which adjusts the number of pods dynamically based on metrics like CPU or memory usage[3][5].

6. **Ease of Management**:
   - Scaling is declarative and automated, requiring minimal manual intervention. You can scale up or down with a single command or configuration change[2][6].

In summary, scaling in Kubernetes ensures high availability, better performance under load, efficient resource utilization, and fault tolerance—all essential for modern applications.

## SCALING 

Absolutely! 💥  
Here’s a **full step-by-step** guide for **Horizontal Scaling**, **Vertical Scaling**, and **Autoscaling** in Kubernetes — made super clean and simple for your notes 📖

---

# 📈 **Horizontal Scaling (Add More Pods)**

### ▶️ Manual Horizontal Scaling
**Steps:**
1. Create a Deployment (if not created already):
   ```bash
   kubectl create deployment pizza-app --image=my-pizza-app-image
   ```

2. Scale the number of pods manually:
   ```bash
   kubectl scale deployment pizza-app --replicas=5
   ```
   👉 Now there will be 5 Pods running!

3. Verify:
   ```bash
   kubectl get pods
   ```

---

### ▶️ Automatic Horizontal Scaling (HPA - Horizontal Pod Autoscaler)
**Steps:**
1. Install Metrics Server (if not installed):
   ```bash
   kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml
   ```

2. Create your app Deployment:
   ```bash
   kubectl create deployment pizza-app --image=my-pizza-app-image
   ```

3. Create an HPA:
   ```bash
   kubectl autoscale deployment pizza-app --cpu-percent=50 --min=1 --max=10
   ```

4. Check HPA status:
   ```bash
   kubectl get hpa
   kubectl describe hpa pizza-app
   ```

✅ **Result**: Pods will automatically increase or decrease based on CPU load!

---

# 📈 **Vertical Scaling (Give More CPU/Memory to Pods)**

### ▶️ Manual Vertical Scaling
**Steps:**
1. Edit the deployment to set resource limits:
   ```bash
   kubectl edit deployment pizza-app
   ```

2. Under `spec.template.spec.containers.resources`, add:
   ```yaml
   resources:
     requests:
       cpu: "500m"
       memory: "512Mi"
     limits:
       cpu: "1000m"
       memory: "1Gi"
   ```

3. Save and exit.  
   (This will **restart** the pods automatically.)

4. Verify:
   ```bash
   kubectl describe pod <pod-name>
   ```

✅ **Result**: Pods will have stronger CPU/memory without increasing the number of pods!

---

### ▶️ Automatic Vertical Scaling (VPA - Vertical Pod Autoscaler)

**Steps (optional):**
- VPA is a bit advanced and not installed by default.
- You need to install VPA components and create VPA objects.
  
(For now, you can mention manual vertical scaling — it’s used 90% of the time.)

---

# 🤖 **Autoscaling (Smart Scaling - Horizontal + Vertical Together)**

**Main Idea:**  
- Use **HPA** for **more pods automatically**  
- Use **VPA** for **stronger pods automatically**  

👉 **In real projects, HPA is more popular.**

---

# 📦 **Summary Cheat-Sheet:**

| Scaling Type | How to do it | Command Example |
|:---|:---|:---|
| Manual Horizontal | Increase Pods manually | `kubectl scale deployment pizza-app --replicas=5` |
| Auto Horizontal | Based on CPU/memory usage | `kubectl autoscale deployment pizza-app --cpu-percent=50 --min=1 --max=10` |
| Manual Vertical | Increase Pod CPU/Memory manually | Edit Deployment YAML under `resources:` |
| Auto Vertical | Adjust CPU/Memory automatically | (Requires installing VPA separately) |

---

# 🎯 **Important Tips:**
- ✅ Always **install Metrics Server** for HPA to work!
- ✅ **Horizontal Scaling** is easier and safer than Vertical Scaling.
- ✅ You can **combine** HPA + VPA for smarter auto-scaling.

---

Would you also like me to create a **sample YAML for HPA** and **sample YAML for Deployment with Resources**? 📄💥  
(You can show those live in your class too!) 🚀