library("tidyverse")
library("Metrics")
library("caret")

dataset <- read.csv("Fish.csv")
head(dataset)
glimpse(dataset)
dim(dataset)

#Checking null values
sum_na <- sum(is.na(dataset))
print(paste("NA Data: ", sum_na))

#Renaming Columns
colnames(dataset)
names(dataset)[names(dataset) == "Length1"] <- "VerticalLen"
names(dataset)[names(dataset) == "Length2"] <- "HorizontalLen"
names(dataset)[names(dataset) == "Length3"] <- "DiagonalLen"
names(dataset)[names(dataset) == "ï..Species"] <- "Species"
colnames(dataset)

#Categorical to numeric
dataset$Species
dataset$Species <- as.numeric(as.factor(dataset$Species)) 
dataset$Species

table(dataset$Species)

#Finding relationship
plot(dataset$Weight~dataset$Height,xlab="Height",ylab="Weight",
     main="Weight vs. Height")

plot(dataset$Weight~dataset$Width,xlab="Width",ylab="Weight",
     main="Weight vs. Width")

plot(dataset$Weight~dataset$DiagonalLen,xlab="Length",ylab="Weight",
     main="Weight vs. Diagonal Length")


#Split data into train and test
set.seed(123)
data.div <- dataset$Weight %>% createDataPartition(p=0.75,list=FALSE)
train.data <- dataset[data.div,]
test.data <- dataset[-data.div,]

dim(train.data)
dim(test.data)


#Multivariate linear regression
lin.multi.model <- lm(formula = Weight~Species+VerticalLen+HorizontalLen+
                  DiagonalLen+Height+Width,data=train.data)
summary(lin.multi.model)


#Univariate Linear regression (width)
lin.uni.width <- lm(formula=Weight~Width,data=train.data)
summary(lin.uni.width)
plot(train.data$Weight~train.data$Width,xlab='Width',ylab='Weight',
     main='Curve fitting weigth vs. width')
abline(lin.uni.width,col='red')


#Univariate Linear Regression (DiagonalLen)
lin.uni.diagonal <- lm(formula=Weight~DiagonalLen,data=train.data)
summary(lin.uni.diagonal)
plot(train.data$Weight~train.data$DiagonalLen,xlab='Length',ylab='Weight',
     main='Curve fitting weigth vs. diagonal length')
abline(lin.uni.diagonal,col='red')


#Prediction (train data)
train.pred.multi <- predict(lin.multi.model,train.data)
train.pred.width <- predict(lin.uni.width,train.data)
train.pred.diagonal <- predict(lin.uni.diagonal,train.data)

#Prediction (test data)
test.pred.multi <- predict(lin.multi.model,test.data)
test.pred.width <- predict(lin.uni.width,test.data)
test.pred.diagonal <- predict(lin.uni.diagonal,test.data)


#MSE calculation (train)
train.mse.multi <- mse(train.pred.multi,train.data$Weight)
train.mse.multi
train.mse.width <- mse(train.pred.width,train.data$Weight)
train.mse.width
train.mse.diagonal <- mse(train.pred.diagonal,train.data$Weight)
train.mse.diagonal

#MSE calculation (test)
test.mse.multi <- mse(test.pred.multi,test.data$Weight)
test.mse.multi
test.mse.width <- mse(test.pred.width,test.data$Weight)
test.mse.width
test.mse.diagonal <- mse(test.pred.diagonal,test.data$Weight)
test.mse.diagonal

trainMSE <- c(train.mse.multi,train.mse.width,train.mse.diagonal)
testMSE <- c(test.mse.multi,test.mse.width,test.mse.diagonal)

#Plotting MSE results
barplot(trainMSE,width=0.01,xlab='Value',ylab='Error (MSE)',main='Train Error')
barplot(testMSE,width=0.01,xlab='Value',ylab='Error (MSE)',main='Test Error')


#R2 Scores for models
R2_multi <- R2(test.pred.multi,test.data$Weight)
R2_multi
R2_width <- R2(test.pred.width,test.data$Weight)
R2_width
R2_diagonal <- R2(test.pred.diagonal,test.data$Weight)
R2_diagonal

#KFold Cross validation
set.seed(123)

train.control <- trainControl(method = "cv", number = 7)
model <- train(Weight ~ ., data = train.data, method = "lm",trControl = train.control)
print(model)
