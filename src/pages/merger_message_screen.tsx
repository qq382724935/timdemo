import React from 'react';
import {TUIMergeMessageScreen} from '../TUIKit';
import type {V2TimMessage} from 'react-native-tim-js';
import {RootStackParamList} from '../interface';


export const MergerMessageScreen = ({route}: any) => {
  const message = route.params.message as V2TimMessage;
  return <TUIMergeMessageScreen message={message} />;
};
