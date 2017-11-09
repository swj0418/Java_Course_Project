import numpy as np
import pandas as pd
import pandas_datareader.data as reader
import datetime

start = datetime.datetime(2015, 1, 1)
end = datetime.datetime(2017, 1, 1)

Frame = reader.DataReader('AAPL', 'yahoo', start, end)
print(Frame)