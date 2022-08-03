OPENSSLVER = ""

RDEPENDS:${PN} += "libssl (>= 1.1.0) libcrypto (>= 1.1.0)"

require summit-supplicant.inc radio-stack-summit-60-version.inc
