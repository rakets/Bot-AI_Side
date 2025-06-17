# Bot AI-Side

## Description

This repository contains the server-side component of a chatbot, implementing algorithmic logic for route processing. The main functionality is to receive a list of points (places to visit) from a Telegram bot ( https://github.com/sierjo/Bot-TelegramSide ), obtain their coordinates using Google Cloud Maps, and build a graph where vertices represent points and edges represent distances between them.

The following algorithms are used to solve the optimal route problem:

- **Genetic Algorithm** — evolutionary search for optimal paths.
- **A*** (A-Star) — shortest path search algorithm in a graph.
- **Local Search** — a method for local improvement of solutions.

The project is written in pure Java 17 without using third-party libraries (except for the Google Cloud Maps API for obtaining coordinates).

Integration with the Telegram bot is planned for the future; currently, the server-side component works autonomously.

## Technologies

- Java 17
- Google Cloud Maps API
- Git
- Docker (for containerization)

## Project Structure

- Receiving a list of points from the Telegram bot ( https://github.com/sierjo/Bot-TelegramSide )
- Obtaining coordinates of points via Google Cloud Maps
- Building a graph with vertices and edges (distances)
- Route optimization using Genetic Algorithm, A*, and Local Search

## Running the Project

The project runs as a Java application. JDK 17 is required.

## Planned Improvements

- Integration with the Telegram bot for two-way communication ( https://github.com/sierjo/Bot-TelegramSide )
- Algorithm optimization

