[ -z "${1}" ] && { echo "Usage: $0 <version>"; exit 1; }

file="radio-stack-sterling-60-hashes.inc"
prefix="https://files.devops.rfpros.com/builds/linux"
ver=${1}

calc_file () {
  name=${1##*/}
  wget ${1} || exit 1
  echo "SRC_URI[${2}.md5sum] = \"$(md5sum ${name} | awk '{print $1}')\"" >> ${file}
  echo "SRC_URI[${2}.sha256sum] = \"$(sha256sum ${name} | awk '{print $1}')\"" >> ${file}
  rm -f ${name}
}

echo -e "PV = \"${ver}\"\n" > ${file}

calc_file "${prefix}/sterling_supplicant/src/${ver}/sterling_supplicant-src-${ver}.tar.gz" "sterling-supplicant"
calc_file "${prefix}/lrd-network-manager/src/${ver}/lrd-network-manager-src-${ver}.tar.xz" "lrd-network-manager"
calc_file "${prefix}/backports/laird/${ver}/backports-laird-${ver}.tar.bz2" "backports-laird"

for i in pcie-uart pcie-usb sdio-uart sdio-sdio usb-usb usb-uart
do
  calc_file "${prefix}/firmware/${ver}/laird-sterling60-firmware-${i}-${ver}.tar.bz2" "sterling60-firmware-${i}"
done
