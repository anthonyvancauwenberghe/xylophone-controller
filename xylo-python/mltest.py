import tensorflow as tf
import numpy as np
import kinematics
import pandas as pd
import keras
import matplotlib.pyplot as plt
import math

train_data = []
labels = []
ds = []
df = []
pt = []

class PrintDot(keras.callbacks.Callback):
  def on_epoch_end(self, epoch, logs):
    if epoch % 100 == 0: print('')
    print('.', end='')

def construct_training_data(n):
    for i in range(0,n):
        train_data.append(kinematics.getEffectorPos(np.random.uniform(0,60,[1,3]).flatten()))
        labels.append([kinematics.getDegs(train_data[i])[0]])

def construct_prediction_table(p):
    td = []
    td.extend(labels)
    td.extend(p)
    column_names = ['S1', 'S2', 'S3', "p1"]
    global pt
    pt = pd.DataFrame(td, columns=column_names)

def build_model():
    data = np.array(train_data)
    model = keras.Sequential([
    keras.layers.Dense(3,  activation=keras.activations.relu,
                            input_shape=(data.shape[1],)),
    keras.layers.Dense(9, activation=keras.activations.tanh),
    keras.layers.Dense(1, activation=keras.activations.linear)
    ])
    optimizer = keras.optimizers.Adam()

    model.compile(  loss='mae',
                    optimizer=optimizer,
                    metrics=['mae'])
    return model

def plot_history(history):
    for i in range(0,len(history.epoch)):
        history.epoch[i] = history.epoch[i]
    plt.figure()
    plt.xlabel('Epochs')
    plt.ylabel('error')
    #plt.plot(history.epoch, np.array(history.history['mean_absolute_error']),
    #         label='Train Loss')
    plt.plot(history.epoch, np.array(history.history['val_mean_absolute_error']),
             label = 'm a e')
    plt.legend()
    plt.ylim([0, 20])
    plt.show()

def plot_accuracy(acc):
    plt.scatter(np.array(labels), acc)
    plt.xlabel('True Values')
    plt.ylabel('Predictions')
    plt.axis('equal')
    plt.xlim(plt.xlim())
    plt.ylim(plt.ylim())
    _ = plt.plot([-100, 100], [-100, 100])
    plt.show()


construct_training_data(500)
data = np.array(train_data)
model = build_model()
model.summary()
EPOCHS = 2000
# Store training stats
history = model.fit(np.array(train_data), np.array(labels), epochs=EPOCHS,
                    validation_split=0.2, verbose=0,
                    callbacks=[PrintDot()])

test_predictions = model.predict(np.array(train_data)).flatten()
print(test_predictions)

plot_history(history)
plot_accuracy(test_predictions)






