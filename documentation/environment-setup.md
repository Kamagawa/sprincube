
# A Comprehensive Documentation

## Index
- [Set up the Kubernetes Environment](#set-up-the-kubernetes-environment)
- [Database Connection](#database-connection)
- [Config Map and Secrets](#config-maps-and-sercrets)
- [Service Discovery](#service-discovery)

## Set up the Kubernetes Environment
Service mesh that enables users to describe network of microservices in discovery, load balancing, failure recovery, metrics and monitoring .
Feature: 
- Traffic management
- Security
- Observability
- Platform support(kubernetes, consul)

**Note: this guide primarily focus on seting up a single cluster via windows platform. However, the Kubernetes documentation offer a detailed insight in set up on a linux system.**

### 1. Prepare a virtualization environment. 
Virtualization enviroment is necessary to allow kubernetes to run in [containerized environment](https://kubernetes.io/docs/tasks/tools/install-minikube/). First enable VT-x or AMD-v virtualization in computer's BIOS
Usually, it is already configured by default. To [do that](https://docs.fedoraproject.org/en-US/Fedora/13/html/Virtualization_Guide/sect-Virtualization-Troubleshooting-Enabling_Intel_VT_and_AMD_V_virtualization_hardware_extensions_in_BIOS.html) 
 1. restart the computer
 2. press F4 or F12 or any of the System menu key to enter the Bios menu
 3. Look for "Chipset, advanced CPU Configuration" or "Northbridge" or othem similar items on different Bios
 4. Enable Intel Virtualization Technoligy, or AMD-V depending on the processor make.
 5. If options avaliable, enable Intel VTd, or AMD IOMMU which are used for PCI passthrough (allows hypervisor)
 6. remember to save before exiting the Bios menu

### 2. Install a Hypervisor
Windnows already have a hypervisor Hyper-V, but install the type of hyperviSor you prefer based on your operation system.
- macOS: VMware Fusion, HyperKit; linux: KVM; Windows: hyper-V, all: virtualBox
follow the installation wizard to finish the set up  

### 3. Prepare a Docker environment
Register and [install docker](https://www.docker.com/). Docker windows for windows enterprise users, docker tool box for other windows users
Or install docker according to your local OS. 

### 3.1 Enable Kubernetes on Docker environment
on Docker for window and Docker for Mac, Kubernetes could be installed along with docker; If using Docker Toolbox, or any other version of Docker that does not come with kubernetes integration, skip this step.
Upon finishing the setup, right click on the icon on the task menu, then navigate to settings->kubernetes

Choose to enable Kubernetes
According to personal preference select or deselect Kubernetes as "Default orchestrator for docker stack commands"

**Note: This installation configures 'Docker for %{YOUR_OS_HERE}' as default context for Kubernetes, if want to run kubernetes in minikube context, right click on the Docker icon -> kubenetes -> d**

### 4. Minikube:
Minikube is recommended to work with a kubernetes app on a **single cluster single node on local machine in virtualized environment**. 
Get Started on a single cluster on local machine, go to https://github.com/kubernetes/minikube/releases, and download and run the installer.

### 5. install Kubernetes
If 3.1 is done, skip this step. To install kubernetes, install kubectl by through a command line install such as 'choco install kubernetes-cli', then run 'kubectl cluster-info' to see if kubernetes is up and running.

### 6. deploy to Cloud service
Kubernetes allow deployment to cloud platforms. [kubeadm](https://kubernetes.io/docs/setup/independent/install-kubeadm/)
is a tool to bootstrap a minimum kubernetes cluste

