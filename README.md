# DDNS Updater

## Overview
**DDNS Updater** is a Java-based tool that automatically updates your Dynu Dynamic DNS (DDNS) hostname with your current dynamic IP address at regular intervals. This allows you to simulate a static IP for your domain even when your ISP provides a dynamic IP.

If you run servers or need remote access to your home network, this tool ensures that your domain points to your current IP without manual updates.

## Features
- **Customizable Update Interval**: Set how often the script checks your IP and updates the Dynu DDNS service.
- **Dynu DDNS API Integration**: Fully integrates with Dynu’s DDNS API for seamless updates.
- **IP Logging**: Tracks and logs IP changes, so you can review when updates occur. (Soon)
- **Cross-Platform**: Runs anywhere Java is supported.

## Prerequisites
Before running the project, ensure you have the following:
- A [Dynu](https://www.dynu.com) account with a DDNS hostname set up.
- API credentials from Dynu (API Key).
- [Java 8+](https://adoptopenjdk.net/) installed.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/redafex/DDNSUpdater.git
   cd DDNSUpdater
   mvn package
