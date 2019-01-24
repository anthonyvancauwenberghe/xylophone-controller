import tensorflow as tf
import numpy as np
import kinematics
import pandas as pd
import keras
import matplotlib.pyplot as plt
import math
import pickle
import os

NET_FILE = "model.net"

def build_model():
    model = keras.Sequential([
    keras.layers.Dense(16,  activation=keras.activations.relu,
                         input_shape=(16,)),
    keras.layers.Dense(16, activation=keras.activations.tanh),
    keras.layers.Dense(16, activation=keras.activations.linear)
        ])
    optimizer = keras.optimizers.Adam()
    model.compile(  loss='mae',
                    optimizer=optimizer,
                    metrics=['mae'])
    return model

def save(model):
    pickle.dump( model, open( NET_FILE, "wb" ) )
def load():
    pickle.load( open( NET_FILE, "rb" ) )

def fitErrors(pos, actual):
    model = None
    if(os.path.isfile(NET_FILE)):
        model = load()
    else:
        model = build_model()
    model.fit(np.array(pos), np.array(actual), epochs=50)
    save(model)









