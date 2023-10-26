/*
 * @Author: 刘利军
 * @Date: 2023-10-26 08:00:50
 * @LastEditors: 刘利军
 * @LastEditTime: 2023-10-26 09:06:31
 * @Description: 
 * @PageName: 
 */
/**
 * Metro configuration for React Native
 * https://github.com/facebook/react-native
 *
 * @format
 */

module.exports = {
  transformer: {
    getTransformOptions: async () => ({
      transform: {
        experimentalImportSupport: false,
        inlineRequires: true,
      },
    }),
  },
};
