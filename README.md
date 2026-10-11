# restful-booker-platform
A platform of web services that forms a Bed and Breakfast booking system. The platforms primary purpose is for  training others on how to explore and test web service platforms as well as strategise and implement automation in testing strategies.

## Requirements
RBP is currently known to work with the following requirements:

- JDK 21.0.5 or higher (Tested with JDK 21)
- Maven 3.6.3
- Node 22.14.0
- NPM 10.9.2

## Building locally

Assuming you have the above requirements in place, to get started open a terminal/command line window and follow these instructions:

1. Clone/Download the repository
2. Navigate into the restful-booker-platform root folder
3. Run either ```bash build_locally.sh``` for Linux or Mac or ```build_locally.cmd``` on Windows to build RBP and get it running (It may take a while on the first run as it downloads dependencies)
4. Navigate to http://localhost:8080 to access the site

## Running locally

Assuming you have successfully built the application at least once, you can now run the app without having to rebuild the whole application.

### Mac / Linux
1. To run without end-to-end checks run: ```run_locally.sh```
2. To run with end-to-end checks run: ```run_locally.sh -e true```

### Windows
1. To run without end-to-end checks run: ```run_locally.cmd```
2. To run with end-to-end checks run: ```run_locally.cmd true```

### Login
The user login details are:
* Username: admin
* Password: password

## Development

### API details

The details on running checks, building APIs and additional details on documentation for development can be found in READMEs inside each of the API folders.


## 🛡️ Security & Cryptographic Posture
This project tracks Software & Cryptographic Bill of Materials (SBOM & CBOM) and Post-Quantum Cryptography (PQC) readiness. See the latest [Cryptographic Audit & PQC Migration Report](docs/security/CRYPTOGRAPHIC_AUDIT.md).

---

## Automated SBOM & CBOM Supply Chain Pipeline

This repository integrates an automated Software Bill of Materials (SBOM) and Cryptographic Bill of Materials (CBOM) supply chain pipeline to satisfy modern security and compliance requirements (Executive Order 14028, NIST SP 800-218 SSDF, and NIST IR 8547 PQC Migration Guidance).

### CI Pipeline Stages

The GitHub Actions workflow [`.github/workflows/sbom-cbom.yml`](.github/workflows/sbom-cbom.yml) executes on every push to `main` and all pull requests:

1. **Dual-Engine SBOM Generation**: Uses `anchore/sbom-action` to produce standard CycloneDX (`oss/sbom.cyclonedx.json`) and SPDX (`oss/sbom.spdx.json`) inventory files.
2. **Cryptographic Bill of Materials (CBOM)**: Uses `cbomkit/cbomkit-action` to discover cryptographic algorithms, certificates, keys, and cryptographic assets (`oss/cbom.json`).
3. **Dual-Engine AST Semantic Crypto Scan**: Executes `scripts/scan_crypto_ast.py` to parse source code abstract syntax trees (ASTs), mapping cryptographic call-sites and cipher initializations to the CBOM inventory.
4. **Automated Verification Harness**: Runs `scripts/test_boms.sh` to validate JSON schemas, verify required CycloneDX attributes, and enforce non-empty inventory thresholds as a build gate.
5. **Post-Quantum Cryptography (PQC) Assessment**: Runs `scripts/analyze_cbom.py` to classify cryptographic assets by quantum-readiness and output markdown summary dashboards to `$GITHUB_STEP_SUMMARY`.
6. **Continuous Governance Publishing**: Automatically syncs the latest cryptographic posture to [`docs/security/CRYPTOGRAPHIC_AUDIT.md`](docs/security/CRYPTOGRAPHIC_AUDIT.md) and publishes all BOM assets to workflow artifacts.

---

## Local Scripts & Verification

All pipeline stages can be executed locally in development environments using scripts in `scripts/`:

### 1. Generating BOMs (`scripts/generate_boms.sh`)

Generate SBOMs and CBOMs for either source directories or Docker container images:

```bash
# Scan current repository directory (outputs to oss/)
bash scripts/generate_boms.sh -t dir .

# Scan a local Docker image
bash scripts/generate_boms.sh -t docker restful-booker-platform-trunk:local

# Specify custom output directory and format
bash scripts/generate_boms.sh -t dir -o build/boms -f cyclonedx-json .
```

### 2. Testing & Validating BOMs (`scripts/test_boms.sh`)

Run automated JSON validation, schema verification, and inventory count checks:

```bash
bash scripts/test_boms.sh oss
```

### 3. Scanning Source AST for Crypto Callsites (`scripts/scan_crypto_ast.py`)

Scan repository source code ASTs for cryptographic primitive invocations and reconcile with an existing CBOM:

```bash
python3 scripts/scan_crypto_ast.py . \
  --cbom oss/cbom.json \
  --output-json oss/crypto-callsites.json
```

### 4. PQC Readiness Analysis (`scripts/analyze_cbom.py`)

Analyze a generated CBOM against software dependencies and generate markdown or JSON risk summaries:

```bash
python3 scripts/analyze_cbom.py oss/cbom.json \
  --sbom oss/sbom.cyclonedx.json \
  --json oss/crypto-analysis.json \
  --markdown docs/security/CRYPTOGRAPHIC_AUDIT.md
```

### 5. OCI Artifact Attachment (`scripts/attach_oci_bom.sh` & `scripts/fetch_oci_cbom.py`)

Publish and retrieve CBOMs attached to OCI container registries:

```bash
# Attach CBOM to container image in registry
bash scripts/attach_oci_bom.sh oss/cbom.json my-registry.io/app:v1.0.0

# Fetch CBOM from container image
python3 scripts/fetch_oci_cbom.py my-registry.io/app:v1.0.0 --output-dir oss
```

---

## Architectural Decision Records (ADRs)

Key architectural decisions governing this repository's security, supply chain, and cryptography strategy are documented under [`docs/adr/`](docs/adr/):

- [**ADR-0001: Automated SBOM and CBOM Supply Chain Governance Pipeline**](docs/adr/0001-automated-sbom-cbom-supply-chain-governance.md) - Context, alternatives considered, decision rationale, and downstream consequences for adopting automated dual-engine SBOM/CBOM scanning, AST call-site analysis, and CI gate verification.

