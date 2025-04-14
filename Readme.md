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