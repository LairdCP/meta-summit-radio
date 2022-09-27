OPENSSLVER = "_legacy_openssl_3_0"

RDEPENDS:${PN} += "libssl (>= 3.0.0) libcrypto (>= 3.0.0)"

require summit-supplicant-legacy.inc radio-stack-4550-version.inc
