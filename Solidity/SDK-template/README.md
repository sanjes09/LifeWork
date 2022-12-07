# {Name} SDK

> A typescript SDK for the {Name} smart contracts
## Setup

```sh
# Install dependencies
yarn
```

```sh
# build package
yarn build

# publish the library
yarn publish
```

## Requirements

```sh
 - provider
 - signer
```

## Modules
[You may find the list of modules here](src/modules/README.md)

## Usage

```sh
import SDK from '{name}-sdk'

const sdk = await SDK.create(provider, signer);

const address = await sdk.modules.usdt.address();
```