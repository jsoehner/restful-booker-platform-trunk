## 🛡️ Cryptographic Bill of Materials (CBOM) & PQC Migration Assessment

**Format**: CycloneDX (v1.6) | **First-Party Code Crypto Assets**: 1 | **Total Tracked Crypto Assets**: 5

### 📊 Post-Quantum Migration Scorecard

| Metric | Count | Migration Status |
|---|---|---|
| **Post-Quantum Ready (PQC)** | **0** | 🟢 Quantum-Resistant (NIST FIPS 203/204/205) |
| **Quantum-Vulnerable (Backlog)** | **3** | 🔴 At Risk of 'Harvest Now, Decrypt Later' |
| **Classical Symmetric / Hashing** | **2** | 🟡 Classical Security (Requires AES-256 / SHA-256+) |
| **Asymmetric PQC Migration Progress** | **0.0%** | (0 of 3 asymmetric primitives migrated) |

### 🎯 Cryptographic Supply Chain Coverage & Confidence

| Evaluation Layer | Coverage / Status | Audit Confidence Assessment |
|---|---|---|
| **First-Party Code (`src/`)** | **100% Audited** (0 Custom Primitives) | 🟢 **HIGH** (Direct AST & SAST verified clean) |
| **Third-Party Supply Chain** | **3.0%** (7 of 230 dependencies cataloged) | 🔴 LOW (Known profiles assimilated) |
| **Overall Audit Confidence Score** | **3.5%** | **🔴 LOW** (223 unassimilated supply chain dependencies) |

### ✅ Post-Quantum Cryptography Migrated Assets

> ⚠️ **No Post-Quantum Ready assets detected.** Immediate migration planning recommended for asymmetric key exchanges and digital signatures.

### ⚠️ Quantum-Vulnerable Assets & Remediation Plan

| Component / Algorithm | Type / Primitive | Key Length / Curve | Recommended Target | Provenance / Context |
|---|---|---|---|---|
| **`spring-web (Spring WebClient / RestTemplate HTTPS)`**<br><sub>HTTPS / TLS 1.3</sub> | assimilated-dependency-crypto / protocol | ECDH-X25519 / RSA-2048 | **Ensure target AI endpoints (e.g. Ollama/OpenAI) and client support hybrid post-quantum key encapsulation (ML-KEM-768)** | Assimilated Upstream CBOM: Spring Framework HTTP Client Security Specification<br>`Dependency: spring-web@4.3.13.RELEASE` |
| **`spring-webmvc (Spring WebClient / RestTemplate HTTPS)`**<br><sub>HTTPS / TLS 1.3</sub> | assimilated-dependency-crypto / protocol | ECDH-X25519 / RSA-2048 | **Ensure target AI endpoints (e.g. Ollama/OpenAI) and client support hybrid post-quantum key encapsulation (ML-KEM-768)** | Assimilated Upstream CBOM: Spring Framework HTTP Client Security Specification<br>`Dependency: spring-webmvc@4.3.13.RELEASE` |
| **`tomcat-embed-core (Tomcat TLS Transport Engine)`**<br><sub>TLS 1.2 / TLS 1.3</sub> | assimilated-dependency-crypto / protocol | RSA-2048 / ECDSA-P256 | **Upgrade to Post-Quantum hybrid key exchange (X25519+ML-KEM-768) via OpenSSL 3.4+ or Java 25 JSSE provider** | Assimilated Upstream CBOM: Apache Tomcat Security Advisory & JSSE Specification<br>`Dependency: tomcat-embed-core@8.5.23` |

### 🔒 Classical Symmetric & Digest Assets

| Component Name | Primitive | Key Length | Quantum Resistance Assessment | Provenance / Location(s) |
|---|---|---|---|---|
| `SHA-384` | hash | 384 | Quantum-Resistant (Grover's proof) | First-Party Code (SAST/AST)<br>`assets/src/app/layout.tsx:24`<br>`assets/src/app/layout.tsx:40`<br>`assets/src/app/layout.tsx:48` |
| `tomcat-embed-core (Tomcat TLS Symmetric Cipher Suites)` | block-cipher | 256 | Quantum-Resistant (Grover's proof) | Assimilated Upstream CBOM: Apache Tomcat Security Advisory & JSSE Specification<br>`Dependency: tomcat-embed-core@8.5.23` |

### ⚠️ Unassimilated Third-Party Binaries & Cryptographic Blind Spots

> ℹ️ *The following third-party dependencies do not have verified upstream CBOM attestations in the catalog. They lower the audit confidence score until explicit CBOMs or attestations are published.* 

| Dependency Name | Version | Package URL (purl) | Status |
|---|---|---|---|
| `@emnapi/runtime` | 1.11.3 | `pkg:npm/%40emnapi/runtime@1.11.3` | 🟡 Unassimilated (No upstream CBOM) |
| `@floating-ui/core` | 1.8.0 | `pkg:npm/%40floating-ui/core@1.8.0` | 🟡 Unassimilated (No upstream CBOM) |
| `@floating-ui/dom` | 1.8.0 | `pkg:npm/%40floating-ui/dom@1.8.0` | 🟡 Unassimilated (No upstream CBOM) |
| `@floating-ui/react` | 0.27.20 | `pkg:npm/%40floating-ui/react@0.27.20` | 🟡 Unassimilated (No upstream CBOM) |
| `@floating-ui/react-dom` | 2.1.9 | `pkg:npm/%40floating-ui/react-dom@2.1.9` | 🟡 Unassimilated (No upstream CBOM) |
| `@floating-ui/utils` | 0.2.12 | `pkg:npm/%40floating-ui/utils@0.2.12` | 🟡 Unassimilated (No upstream CBOM) |
| `@img/colour` | 1.1.0 | `pkg:npm/%40img/colour@1.1.0` | 🟡 Unassimilated (No upstream CBOM) |
| `@img/sharp-darwin-arm64` | 0.35.5 | `pkg:npm/%40img/sharp-darwin-arm64@0.35.5` | 🟡 Unassimilated (No upstream CBOM) |
| `@img/sharp-darwin-x64` | 0.35.5 | `pkg:npm/%40img/sharp-darwin-x64@0.35.5` | 🟡 Unassimilated (No upstream CBOM) |
| `@img/sharp-freebsd-wasm32` | 0.35.5 | `pkg:npm/%40img/sharp-freebsd-wasm32@0.35.5` | 🟡 Unassimilated (No upstream CBOM) |
| `@img/sharp-libvips-darwin-arm64` | 1.3.4 | `pkg:npm/%40img/sharp-libvips-darwin-arm64@1.3.4` | 🟡 Unassimilated (No upstream CBOM) |
| `@img/sharp-libvips-darwin-x64` | 1.3.4 | `pkg:npm/%40img/sharp-libvips-darwin-x64@1.3.4` | 🟡 Unassimilated (No upstream CBOM) |
| `@img/sharp-libvips-linux-arm` | 1.3.4 | `pkg:npm/%40img/sharp-libvips-linux-arm@1.3.4` | 🟡 Unassimilated (No upstream CBOM) |
| `@img/sharp-libvips-linux-arm64` | 1.3.4 | `pkg:npm/%40img/sharp-libvips-linux-arm64@1.3.4` | 🟡 Unassimilated (No upstream CBOM) |
| `@img/sharp-libvips-linux-ppc64` | 1.3.4 | `pkg:npm/%40img/sharp-libvips-linux-ppc64@1.3.4` | 🟡 Unassimilated (No upstream CBOM) |
| *... and 208 more unassimilated dependencies* | | | |
