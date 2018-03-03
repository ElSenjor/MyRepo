'''
Created on 18.02.2018

@author: Alex-admin
'''
import os
import time
import requests
import cv2
import numpy as np
from scipy.misc import imresize


def loadImage(url, VERBOSE=False):
    try:
        res = requests.get(url)
        if VERBOSE:
            print('loading image...')
        with open('image2.jpg', 'wb') as fp: 
            for chunk in res: 
                fp.write(chunk)
        
        img_arr = cv2.imread('image2.jpg',1)
        return img_arr
    except requests.exceptions.RequestException as e:
        return None

def load_image_retry():
    new_name= input('Type again / Type "skip" to skip:')
    #TODO add automatic request
    return new_name

def getImgLikeTxt(path):
    imgs = None
    with open(path,'r') as f:
        lines=f.read()
    lines = lines.split('\n')
    for line in lines:
        line = line.split('_')
        n = int(line[0])
        name = line[1]
        url = 'http://yugiohprices.com/api/card_image/'+name
        req = loadImage(url)
        if req is not None:
            print('\nadding {} copie(s) of {}'.format(n,name))
            if req.shape[0]!=500 or req.shape[1]!=341:
                print('reshaping image {} from {} to 500x341'.format(name,req.shape))
                if req.shape[0]==500 and req.shape[1]>341:
                    req = req[:,:341]
                elif req.shape[0]==500 and req.shape[1]<341:
                    filler = np.zeros([500,341-req.shape[1],3])
                    req = np.append(req,filler,axis=1)
                else:
                    print('Error, please write Code to format from this shape to 500x341')
            req = np.expand_dims(req,axis=0)
            
            #Bild fertig, adding n copies to np Array
            for _ in range(n):
                if imgs is not None:
                    imgs=np.append(imgs,req, axis=0)
                else:
                    imgs = req
                print('added '+name)
        else: 
            print('Image '+name+' could not be loaded')
            name = load_image_retry()
            lines.append(str(n)+'_'+name)
            print('Adding {} at the end\n'.format(name))
    print('{} Cards loaded\n'.format(imgs.shape[0]))
    return imgs
#Variables
dirpath = os.path.abspath(os.path.join(os.path.dirname(__file__),'..'))
imgPath = os.path.abspath(dirpath + '/imgs/'+time.strftime('%m%d_%H%M', time.gmtime()))
txtPath = os.path.abspath(dirpath + '/cards.txt')

if not os.path.exists(imgPath):
    os.makedirs(imgPath)
imgs = getImgLikeTxt(txtPath)
if imgs.shape[0]%9!=0:
    filler = np.full([9-imgs.shape[0]%9,500,341,3],np.finfo(imgs.dtype).max)
    imgs = np.append(imgs, filler, axis=0)
imgs = np.reshape(imgs,[-1,500*3,341,3])

print('Saving images...')
for i in range(int(imgs.shape[0]/3)):
        print('writing image {}/{}'.format(str(i*9+9),str(imgs.shape[0]*3)))
        imgToPrint = np.append(np.append(imgs[i*3,:,:,:],imgs[i*3+1,:,:,:],axis=1),imgs[i*3+2,:,:,:], axis=1)
        cv2.imwrite(imgPath+'/created_image{}.png'.format(str(i)),imgToPrint)
print('Printable pngs written to '+imgPath)
