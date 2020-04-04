
# coding: utf-8

# ## Setup

# In[1]:


import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
get_ipython().run_line_magic('matplotlib', 'inline')
import numpy as np
import warnings
warnings.filterwarnings('ignore')


# In[2]:


df_train= pd.read_csv('Training.csv')


# ### Explore the data

# In[3]:


df_train.head()


# In[4]:


df_train.shape


# In[5]:


df_train.dtypes


# In[6]:


df_train.info()


# In[7]:


df_train.describe()


# In[13]:


#seeing any null values are there with descending format
df_train.isnull().sum().sort_values(ascending=False)


# In[18]:


df_train['prognosis'].unique()
#41 disease


# In[15]:


df_train['prognosis'].value_counts(normalize = True).plot.bar()
plt.subplots_adjust(left = 0.9, right = 2 , top = 2, bottom = 1)


# In[16]:


#Analyzing each symptoms/variable
for x in range(df_train.shape[1]):
    plt.subplot(7,22,x+1)
    plt.subplots_adjust(left = 0.5, right = 16 , top = 10, bottom = 0.5)
    sns.countplot(df_train[df_train.columns[x]]).set_title(df_train.columns[x],fontsize=23)


# ## Machine Learning 

# In[19]:


X = df_train.drop(['prognosis'],axis =1)
y = df_train['prognosis']


# In[20]:


from sklearn.model_selection import train_test_split


# In[21]:


X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.33, random_state=42)


# In[22]:


from sklearn.naive_bayes import MultinomialNB


# In[23]:


mnb = MultinomialNB()
mnb = mnb.fit(X_train, y_train)


# In[24]:


y_pred = mnb.predict(X_test)


# In[25]:


y_pred


# In[26]:


mnb.score(X_test, y_test)


# In[27]:


from sklearn.model_selection import cross_val_score


# In[28]:


scores = cross_val_score(mnb, X_test, y_test, cv=3)
print (scores)
print (scores.mean())


# #### serialization 

# In[29]:


from sklearn.externals import joblib


# In[30]:


# Save the model to disk
joblib.dump(mnb, 'final_model.pkl')


# In[ ]:


#Deserialization
#mnb = joblib.load('model.pkl')


# In[31]:


model_columns = list(X.columns)
joblib.dump(model_columns, 'model_columns.pkl')

