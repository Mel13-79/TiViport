# TiViport

**A free, open-source IPTV player for Android TV, Fire TV, phones and tablets.**

No account. No subscription. No server of ours between you and your provider.


[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE)


---



## Why this exists

There is a recurring pattern in this corner of the app world. A promising IPTV player appears.
It looks good. It sells a "lifetime" subscription. The lifetime turns out to be shorter than
the buyer's — the developer burns out, or moves on, or simply stops replying, and everyone who
paid is left with an app that no longer works and no way to fix it.

The people affected are not helpless. They are often technical. What they lack is not skill
but *access*: the code is closed, so nobody else can pick it up.

TiViport is the same category of app built so that cannot happen. The source is public and
GPL-licensed. If the current maintainers vanish tomorrow, anyone can fork it, build it and
keep it alive. That is the entire point.

**TiViport is not a fork or a decompilation of any existing app.** It is written from scratch.

## What it does

- **Live TV** from Xtream Codes logins and plain M3U/M3U8 playlists
- **A guide that stays put** — XMLTV EPG that survives restarts, refreshes incrementally, and
  never wipes itself
- **Recording (DVR)** — record to the box, a USB drive or your NAS (SMB); schedule from the
  guide; series-link a whole show; play back in-app with full seeking
- **Catch-up / archive** where your provider offers it, plus **programme reminders**
- **Movies and series** with resume and per-profile watch history
- **Favourites, categories, hide channels, search**, picture-in-picture, aspect control, and
  hand-off to an external player
- **Free sync between your own devices** — favourites, watch history and NAS recordings, over
  your wifi or Tailscale, with no server of ours
- **Multiple languages** (fully translated to Spanish), a parental PIN, profiles, and self-updates
- **D-pad first** — designed for a remote, works with a touchscreen
- **One APK** for Android TV, Fire TV, phones and tablets

## Screenshots

The live TV guide — programme grid, favourites, and a live preview:

![TiViport live TV guide](docs/screenshots/01-guide.png)

| Recordings &amp; reminders | Free cloud sync — your own NAS, no server |
| :---: | :---: |
| ![Recordings and reminders](docs/screenshots/02-recordings.png) | ![NAS cloud sync](docs/screenshots/03-cloud-sync.png) |
| Movies &amp; series | Settings |
| ![Movies](docs/screenshots/04-movies.png) | ![Settings](docs/screenshots/05-settings.png) |

Open-source through and through — the About screen carries the licence, the links, and a donation QR:

![About](docs/screenshots/06-about.png)

## What it deliberately does not do

- **No servers of ours, no web dashboard, no account.** Your provider credentials never leave
  your device, and syncing happens directly between your devices — or through your own NAS —
  never through a machine we run. This is a feature, not a gap — see
  [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) for the reasoning, and note that "sync your
  channel list to *our* servers" is precisely the feature whose hosting bill makes a one-off
  payment unsustainable.
- **No content.** TiViport is a player. You bring a service you already pay for. The project has
  no affiliation with any provider and does not help you find one.

## Install










## Licence

[GPL-3.0-or-later](LICENSE).


## Legal

TiViport is a media player, comparable to VLC. It ships with no channels, no playlists, and no
links to any. What you point it at, and whether you are entitled to, is between you and your
provider.
