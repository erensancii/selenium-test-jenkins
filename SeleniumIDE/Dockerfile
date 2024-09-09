FROM maven:3.9.0-eclipse-temurin-17

# Çalışma dizinini ayarla
WORKDIR /usr/src/app

# Gereken bağımlılıkları yükleyin
RUN apt-get update && apt-get install -y \
   wget \
   unzip \
   curl \
   xvfb \
   libglib2.0-0 \
   libnss3 \
   libgconf-2-4 \
   libfontconfig1 \
   libx11-xcb1 \
   libxcb1 \
   libxcomposite1 \
   libxcursor1 \
   libxdamage1 \
   libxext6 \
   libxi6 \
   libxrandr2 \
   libxtst6 \
   libappindicator3-1 \
   libasound2 \
   libatk-bridge2.0-0 \
   libatspi2.0-0 \
   libdbus-1-3 \
   libgtk-3-0

RUN wget --no-verbose -O /tmp/chrome.deb https://chrome-versions.com/google-chrome-stable-114.0.5735.90-1.deb 
RUN dpkg -i /tmp/chrome.deb || apt-get -f install -y && rm /tmp/chrome.deb
RUN wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/114.0.5735.90/chromedriver_linux64.zip 
RUN unzip /tmp/chromedriver.zip -d /usr/local/bin/
RUN rm /tmp/chromedriver.zip
#yetki verme
RUN chmod +x /usr/local/bin/chromedriver 

# Proje dosyalarını kopyalayın
COPY . /usr/src/app

# Maven ile proje build et
RUN mvn clean install
