## Debug

error: gpg failed to sign the data:
[GNUPG:] KEY_CONSIDERED 0C06747D42B18B3F72D97DD6C96E2AE63056C814 2
[GNUPG:] BEGIN_SIGNING H10
[GNUPG:] PINENTRY_LAUNCHED 21282 curses 1.2.1 - - /private/tmp/com.apple.launchd.AclrC5BoVJ/org.xquartz:0 - 501/20 0
gpg: signing failed: Inappropriate ioctl for device
[GNUPG:] FAILURE sign 83918950
gpg: signing failed: Inappropriate ioctl for device

fatal: failed to write commit object when I already put GPG_TTY=$(tty)

Solution : https://gist.github.com/paolocarrasco/18ca8fe6e63490ae1be23e84a7039374


