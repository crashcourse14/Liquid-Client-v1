export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH


chmod +x ./gradlew 
./gradlew build

./gradlew clean teavm

chmod +x ./CompileEPK.sh
./CompileEPK.sh

chmod +x ./ZipStableDownload.sh
./ZipStableDownload.sh


export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
