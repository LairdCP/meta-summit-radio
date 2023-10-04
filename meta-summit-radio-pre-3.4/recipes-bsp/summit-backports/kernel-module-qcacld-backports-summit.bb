SUMMARY = "Summit Backports for QCACLD"

BACKPORTS_CONFIG = "${@bb.utils.contains('DISTRO_FEATURES','bluetooth','qcacld','qcacld_nbt',d)}"

RCONFLICTS_${PN} = " \
        kernel-module-60-backports-summit \
        kernel-module-lwb-backports-summit \
        kernel-module-msd45-backports-summit \
        kernel-module-msd50-backports-summit \
        kernel-module-nx-backports-summit \
        "

require summit-backports.inc radio-stack-qcacld-version.inc
