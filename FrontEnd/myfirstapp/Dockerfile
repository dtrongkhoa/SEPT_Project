FROM node:14
ENV PATH /node_modules/.bin:$PATH

COPY package*.json ./
COPY package-lock.json ./
RUN npm install --silent
RUN npm install react-scripts@3.4.1 -g --silent

COPY . ./

EXPOSE 3000

CMD [ "npm", "start" ]
