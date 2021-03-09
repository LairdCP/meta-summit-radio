DEPENDS += "kernel-module-backports-laird"

NOSTDINC_FLAGS = " \
        -I${STAGING_INCDIR}/kernel-module-backports-laird/backport-include \
        -I${STAGING_INCDIR}/kernel-module-backports-laird/include \
        -include backport/backport.h \
        "

EXTRA_OEMAKE += "NOSTDINC_FLAGS='${NOSTDINC_FLAGS}'"
