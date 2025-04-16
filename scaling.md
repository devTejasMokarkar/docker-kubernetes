Below is a document containing only the successful commands from the provided file, formatted for clarity. Each command is listed as it appeared in the original document, excluding any commands that resulted in errors or failures (e.g., `kubectl logs` for non-existent pods, `kubectl edit` with no changes, or commands like `kubectl nano` that are invalid).

---

# Successful Kubernetes Commands for Metrics Server and HPA Configuration

This document lists the successful Kubernetes commands executed in the provided terminal session for configuring the Metrics Server and Horizontal Pod Autoscaler (HPA) for the `demo-app` deployment.

## Commands

1. **Patch Metrics Server Deployment to Allow Insecure TLS**
   ```bash
   kubectl patch deployment metrics-server -n kube-system \
     --type='json' \
     -p='[{"op": "add", "path": "/spec/template/spec/containers/0/args/-", "value": "--kubelet-insecure-tls"}]'
   ```
   **Outcome**: Successfully patched the `metrics-server` deployment to include the `--kubelet-insecure-tls` argument.

2. **Restart Metrics Server Deployment**
   ```bash
   kubectl rollout restart deployment metrics-server -n kube-system
   ```
   **Outcome**: Successfully restarted the `metrics-server` deployment to apply the patched configuration.

3. **List Pods in kube-system Namespace**
   ```bash
   kubectl get pods -n kube-system
   ```
   **Outcome**: Successfully retrieved the list of pods in the `kube-system` namespace, showing the status of `metrics-server` and other system pods.

4. **Retrieve Metrics Server Deployment YAML**
   ```bash
   kubectl get deployment metrics-server -n kube-system -o yaml | grep args -A 10
   ```
   **Outcome**: Successfully extracted the `args` section of the `metrics-server` deployment YAML, confirming the inclusion of `--kubelet-insecure-tls`.

5. **Retrieve Metrics Server Pod Logs**
   ```bash
   kubectl logs metrics-server-549d869854-7dzrd -n kube-system
   ```
   **Outcome**: Successfully retrieved logs for the `metrics-server-549d869854-7dzrd` pod, showing the server starting and serving metrics.

6. **Check Pod Resource Usage**
   ```bash
   kubectl top pods
   ```
   **Outcome**: Successfully retrieved CPU and memory usage for the `demo-app` pods after the Metrics Server was fully operational.

7. **List Pods with app=demo-app Label**
   ```bash
   kubectl get pods -l app=demo-app -o wide
   ```
   **Outcome**: Successfully listed the `demo-app` pods with detailed information, including IP addresses and node assignments.

8. **Retrieve Node Metrics**
   ```bash
   kubectl get --raw "/apis/metrics.k8s.io/v1beta1/nodes" | jq
   ```
   **Outcome**: Successfully retrieved node metrics in JSON format, confirming the Metrics Server API was operational.

9. **Check Metrics API Service**
   ```bash
   kubectl get apiservices | grep metrics
   ```
   **Outcome**: Successfully confirmed the `v1beta1.metrics.k8s.io` API service was available and managed by the `metrics-server`.

10. **Patch HPA for CPU Utilization Target**
    ```bash
    kubectl patch hpa demo-app -p '{"spec":{"metrics":[{"type":"Resource","resource":{"name":"cpu","target":{"type":"Utilization","averageUtilization":10}}}]}}'
    ```
    **Outcome**: Successfully updated the `demo-app` HPA to target 10% CPU utilization.

11. **Delete and Recreate HPA for demo-app**
    ```bash
    kubectl delete hpa demo-app
    kubectl autoscale deployment demo-app --cpu-percent=10 --min=1 --max=10
    ```
    **Outcome**: Successfully deleted the existing `demo-app` HPA and recreated it with a 10% CPU target, 1 minimum replica, and 10 maximum replicas.

12. **Patch demo-app Deployment with Resource Requests and Limits**
    ```bash
    kubectl patch deployment demo-app -p '{"spec":{"template":{"spec":{"containers":[{"name":"demo-app","resources":{"requests":{"cpu":"100m"},"limits":{"cpu":"500m"}}}]}}}}'
    ```
    **Outcome**: Successfully updated the `demo-app` deployment to include CPU resource requests (100m) and limits (500m), enabling HPA scaling.

13. **Describe HPA for demo-app**
    ```bash
    kubectl describe hpa demo-app
    ```
    **Outcome**: Successfully retrieved detailed information about the `demo-app` HPA, showing scaling events and CPU utilization metrics after resource requests were added.

14. **Get demo-app Deployment**
    ```bash
    kubectl get deployment demo-app
    ```
    **Outcome**: Successfully retrieved the status of the `demo-app` deployment, confirming 3/3 replicas were ready.

15. **Get HPA for demo-app**
    ```bash
    kubectl get hpa
    ```
    **Outcome**: Successfully retrieved the `demo-app` HPA status, showing the CPU target and current replica count.

16. **Get Container Images for Pods**
    ```bash
    kubectl get pods -o=jsonpath='{.items[*].spec.containers[*].image}'
    ```
    **Outcome**: Successfully retrieved the container images for all pods, confirming `demo-app:latest` was used.

---

## Notes
- The commands above focus on the successful configuration and monitoring of the Metrics Server and the `demo-app` HPA.
- The Metrics Server was patched to allow insecure TLS connections, restarted, and verified to be operational, enabling pod metrics for `kubectl top` and HPA scaling.
- The `demo-app` deployment was updated with CPU resource requests, allowing the HPA to scale based on CPU utilization, eventually stabilizing at 10 replicas.
- Commands that failed (e.g., `kubectl logs` for non-existent pods, invalid `kubectl nano`, or `kubectl edit` with no changes) are excluded to focus on the successful workflow.

--- 

This document provides a clear sequence of commands that successfully configured the Kubernetes environment for metrics collection and autoscaling.