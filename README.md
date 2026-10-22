# BaldMark Client — Fabric 1.21.11 GUI prototype

Original purple/black client UI inspired by lightweight Minecraft clients. This is **source code**, not a compiled or tested JAR.

## Implemented in this source
- Purple compact HUD with live XYZ coordinates (`H` toggles the HUD).
- Feather-style main menu (`Right Shift` opens/closes it).
- Separate PvP ClickGUI screen.
- GUI buttons for HUD and coordinate settings.
- Prototype toggles for Player ESP, X-Ray, Ancient Debris Finder, and Base ESP. These currently only change the displayed ON/OFF state; they do **not** implement those gameplay features.

## Ely.by / Zalith
BaldMark is a client-side Fabric mod and does not implement account authentication. Use a Zalith setup that already supports your Ely.by account and Minecraft version. The mod cannot make an incompatible login/session work.

## Build locally
Requirements: JDK 21 and Gradle 8.14.3 (or compatible).

```sh
gradle build
```

Output should be under `build/libs/`. This project has not been compiled in this environment, so compatibility is not yet verified. If the build fails, check the Minecraft/Fabric versions at https://fabricmc.net/develop/.

## Build online with GitHub Actions
1. Download and extract this ZIP.
2. Create a new GitHub repository and upload the extracted project files (including `.github/workflows/build.yml`).
3. Open the repository's **Actions** tab and enable Actions if prompted.
4. Select **Build BaldMark Client**, click **Run workflow**, and wait for the run to finish.
5. Open the completed run and download the artifact named `BaldMark-Fabric-1.21.11`.
6. Only if the workflow succeeds, copy the generated JAR into the `mods` folder of a Minecraft 1.21.11 Fabric instance in Zalith, along with a compatible Fabric API JAR.

## Multiplayer note
Only use gameplay-modifying features where permitted by the server. This starter does not yet implement X-Ray, ESP, debris scanning, or base detection, and it cannot reveal data the server has not sent to the client.
