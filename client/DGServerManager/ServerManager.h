//
//  ServerManager.h
//  DGServerManager
//
//  Created by apple on 2018/3/22.
//  Copyright © 2018年 apple. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GameInfo.h"
#import "PushGameInfo.h"

typedef void (^backSuccess)(BOOL success);

@interface ServerManager : NSObject

@property (nonatomic, strong) NSMutableDictionary *dataDoc; //模型数据集
@property (nonatomic, strong) NSMutableDictionary *pushDataDoc; //模型数据集
@property (nonatomic, strong) NSMutableDictionary *pushStatictics; //模型数据集

+ (ServerManager*)shareServer;

- (void)pullGameInfo:(backSuccess) success;

- (void)startPullPushData:(NSString*)gameCode back:(backSuccess)success;

- (void)startModifiyPushData:(NSString*)gameCode PushGame:(NSString*)pushGameCode perent:(NSNumber*)percent back:(backSuccess)success;

- (void)startGetStatictics:(NSString*)dateString back:(backSuccess)success;

- (NSNumber*)getGameIdByCode:(NSString*)gameCode;

@end
