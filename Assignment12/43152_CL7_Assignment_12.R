library("tidyverse")
wine.data <- read.csv("winequalityN.csv")
head(wine.data)
dim(wine.data)
colnames(wine.data)
summary(wine.data)
glimpse(wine.data)
#View(wine.data)

#converting "type" column to numeric type required for PCA
wine.data$type <- ifelse(wine.data$type == "white",1,0)
table(wine.data$type)

#checking null values
sum_na <- sum(is.na(wine.data))
print(paste("NA Data: ", sum_na))

#removing data with null values in columns (38 records)
wine.data <- wine.data[complete.cases(wine.data),]

#attach dataset to directly refer columns by name instead of using $
attach(wine.data)

#store wine quality in variable target 
target <- wine.data[,ncol(wine.data)]
length(target)

#choose all other columns of the dataframe for PCA
X <- cbind(type,fixed.acidity,volatile.acidity,citric.acid,residual.sugar,chlorides,free.sulfur.dioxide,total.sulfur.dioxide,density,pH,sulphates,alcohol)
class(X)

#find Correlation-Matrix
X
cor(X)

#Perform PCA
pcomp <- princomp(X,scores=TRUE,cor=TRUE)
summary(pcomp)

loadings(pcomp)

#Visualize to find relevant components according to variance values
plot(pcomp,main="Variance vs. Components",cex.axis=0.75)
plot(pcomp,main="Variance vs. Components",t='l',cex.axis=0.75)
biplot(pcomp)

attributes(pcomp)
pcomp$scores[1:10,]
