# NewCryptoFramework

- [NewCryptoFramework](#newcryptoframework)
- [Architecture](#architecture)
  - [Encryption](#encryption)
  - [Persistence](#persistence)

This is a common set of APIs for storing serialized data to files in an Android environment.  Although the framework is built to run on Android, like any Java code, it could be used in a desktop application as well.

# Architecture
## Encryption
NewCryptoFramework uses the [SpongyCastle](https://rtyley.github.io/spongycastle/) (an Android version of [Bouncy Castle](https://www.bouncycastle.org/)) encryption library.  Encryption is handled by a few specialized classes that delegate to this library.

![](./docs/res/SFS-Crypto.svg)

## Persistence

![](./docs/res/SFS-Persistence%20Framework.svg)
