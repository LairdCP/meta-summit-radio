[ -z "${1}" ] && { echo "Usage: $0 <version>"; exit 1; }

file="radio-stack-60-hashes.inc"
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

for i in x86 x86_64 arm-eabi arm-eabihf aarch64 powerpc64-e5500
do
  calc_file "${prefix}/adaptive_ww/laird/${ver}/adaptive_ww-${i}-${ver}.tar.bz2" "adaptive-ww-${i}"
  calc_file "${prefix}/summit_supplicant/laird/${ver}/summit_supplicant_libs-${i}-${ver}.tar.bz2" "summit-supplicant-libs-${i}"
done

calc_file "${prefix}/summit_supplicant/laird/${ver}/summit_supplicant-src-${ver}.tar.gz" "summit-supplicant-src"
calc_file "${prefix}/adaptive_bt/src/${ver}/adaptive_bt-src-${ver}.tar.gz" "adaptive-bt"
calc_file "${prefix}/lrd-network-manager/src/${ver}/lrd-network-manager-src-${ver}.tar.xz" "summit-network-manager"
calc_file "${prefix}/backports/laird/${ver}/backports-laird-${ver}.tar.bz2" "summit-backports"

for i in pcie-uart pcie-usb sdio-uart sdio-sdio usb-usb usb-uart
do
  calc_file "${prefix}/firmware/${ver}/laird-60-radio-firmware-${i}-${ver}.tar.bz2" "60-firmware-${i}"
done

