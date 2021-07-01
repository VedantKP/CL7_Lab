library(arules)
library(arulesViz)
library(tidyverse)
library(readxl)
library(plyr)

online.data <- read_excel("Online Retail.xlsx")
head(online.data)

online.data <- online.data[complete.cases(online.data),]

online.data %>% mutate(Description=as.factor(Description))
online.data$InvoiceDate <- as.Date(online.data$InvoiceDate)
online.data$InvoiceNo <- as.numeric(online.data$InvoiceNo)
head(online.data)
dim(online.data)

glimpse(online.data)

transactionsFile <- ddply(online.data,c("InvoiceNo","InvoiceDate"),
                          function(online.data)paste(online.data$Description,
                                                     collapse=","))
transactionsFile$InvoiceNo <- NULL 
transactionsFile$InvoiceDate <- NULL
colnames(transactionsFile) <- c("Items")

head(transactionsFile)

write.csv(transactionsFile,file="OnlineDataTransactions.csv",quote=FALSE,row.names=FALSE)

transData <- read.transactions("OnlineDataTransactions.csv",format="basket",sep=",")
transData
summary(transData)

itemFrequencyPlot(transData,topN=8,type="absolute",col="green1",
                  xlab="Item",ylab="Count",main="Absolute Frequency")

rules <- apriori(transData,parameter=list(support=0.001,confidence=0.8))

inspect(rules[1:10,])

top10rules <- head(rules,n=10,by='confidence')
plot(top10rules,method="graph",engine="htmlwidget")
