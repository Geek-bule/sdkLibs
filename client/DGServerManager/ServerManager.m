//
//  ServerManager.m
//  DGServerManager
//
//  Created by apple on 2018/3/22.
//  Copyright © 2018年 apple. All rights reserved.
//

#import "ServerManager.h"
#import "AFNetworking.h"


//#define ZY_HOST                         @"http://192.172.1.107:8080"
#define ZY_HOST                         @"http://www.syduogu.com:8080/"

#define ZY_URL_GETGAME                   @"/app/v1/web/game/get"
#define ZY_URL_RECOMMEND                 @"app/v1/push/recommend"
#define ZY_URL_ADDGAME                   @"/app/v1/web/game/add"
#define ZY_URL_UPDATEGAME                @"/app/v1/web/game/update"
#define ZY_URL_MODIFIYPUSH               @"/app/v1/web/push/modify"
#define ZY_URL_STATICTICS               @"/app/v1/web/statistics/get"



@implementation ServerManager


+ (ServerManager*)shareServer
{
    static ServerManager* s_share = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        s_share = [[ServerManager alloc] init];
    });
    return s_share;
}

- (id)init {
    self = [super init];
    if (self) {
        self.dataDoc = [[NSMutableDictionary alloc] init];
        self.pushDataDoc = [[NSMutableDictionary alloc] init];
        self.pushStatictics = [[NSMutableDictionary alloc] init];
    }
    return self;
}

- (NSNumber*)getGameIdByCode:(NSString*)gameCode
{
    for (NSString *key in [self.dataDoc allKeys]) {
        GameInfo *obj = self.dataDoc[key];
        if ([obj.code isEqualToString:gameCode]) {
            int gameId = key.intValue;
            return [NSNumber numberWithInt:gameId];
        }
    }
    
    return [NSNumber numberWithInt:-1];
}

- (void)pullGameInfo:(backSuccess) success
{
    NSString *url = [NSString stringWithFormat:@"%@/%@",ZY_HOST,ZY_URL_GETGAME];
    
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.responseSerializer = [AFHTTPResponseSerializer serializer];
    NSLog(@"多谷sdk:游戏列表=>%@",url);
    
    [manager GET:url parameters:nil progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingMutableLeaves error:nil];
        
        NSLog(@"多谷sdk:游戏列表<=%@",dic);
        NSString* code = dic[@"code"];
        if (code && code.intValue == 0) {
            [self.dataDoc removeAllObjects];
            NSArray* dataList = dic[@"content"];
            for (id value in dataList) {
                //game info
                GameInfo *gameInfo = [[GameInfo alloc] initWithDictionary:value];
                [self.dataDoc setObject:gameInfo forKey:gameInfo.id];
            }
            //成功回调
            if (success) {
                success(YES);
            }
        }else{
            NSString* message = dic[@"msg"];
            NSLog(@"多谷sdk:游戏列表－%@",message);
            if (success) {
                success(NO);
            }
        }
        
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        NSLog(@"多谷sdk:游戏列表异常 -%@", error);
        if (success) {
            success(NO);
        }
    }];
}


- (void)startPullPushData:(NSString*)gameCode back:(backSuccess)success
{
    NSMutableDictionary *parameters = [[NSMutableDictionary alloc] init];
    [parameters setObject:@"ios" forKey:@"mobileType"];
    [parameters setObject:gameCode forKey:@"gameCode"];\
    [parameters setObject:@"d0000000007" forKey:@"dgUdid"];
    
    [self pullPushData:parameters back:success];
}

- (void)pullPushData:(NSDictionary*)parameters back:(backSuccess)success
{
    NSString *url = [NSString stringWithFormat:@"%@/%@",ZY_HOST,ZY_URL_RECOMMEND];
    
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.responseSerializer = [AFHTTPResponseSerializer serializer];
    NSLog(@"多谷sdk:推荐游戏=>%@",url);
    
    [manager POST:url parameters:parameters progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingMutableLeaves error:nil];
        
        NSLog(@"多谷sdk:推荐游戏<=%@",dic);
        NSString* code = dic[@"code"];
        if (code && code.intValue == 0) {
            [self.pushDataDoc removeAllObjects];
            NSArray* dataList = dic[@"content"];
            for (id value in dataList) {
                //game info
                PushGameInfo *gameInfo = [[PushGameInfo alloc] initWithDictionary:value];
                [self.pushDataDoc setObject:gameInfo forKey:gameInfo.gameCode];
            }
            if (success) {
                success(YES);
            }
        }else{
            NSString* message = dic[@"msg"];
            NSLog(@"多谷sdk:推荐游戏－%@",message);
            if (success) {
                success(NO);
            }
        }
        
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        NSLog(@"多谷sdk:推荐游戏异常 -%@", error);
        if (success) {
            success(NO);
        }
    }];
}


- (void)startModifiyPushData:(NSString*)gameCode PushGame:(NSString*)pushGameCode perent:(NSNumber*)percent back:(backSuccess)success
{
    NSMutableDictionary *parameters = [[NSMutableDictionary alloc] init];
    [parameters setObject:@"ios" forKey:@"mobileType"];
    [parameters setObject:gameCode forKey:@"gameCode"];
    [parameters setObject:pushGameCode forKey:@"pushGameCode"];
    [parameters setObject:percent forKey:@"percent"];
    
    [self modifiyPushData:parameters back:success];
}

- (void)modifiyPushData:(NSDictionary*)parameters back:(backSuccess)success
{
    NSString *url = [NSString stringWithFormat:@"%@/%@",ZY_HOST,ZY_URL_MODIFIYPUSH];
    
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.responseSerializer = [AFHTTPResponseSerializer serializer];
    NSLog(@"多谷sdk:修改推荐=>%@",url);
    
    [manager POST:url parameters:parameters progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingMutableLeaves error:nil];
        
        NSLog(@"多谷sdk:修改推荐<=%@",dic);
        NSString* code = dic[@"code"];
        if (code && code.intValue == 0) {
            if (success) {
                success(YES);
            }
        }else{
            NSString* message = dic[@"msg"];
            NSLog(@"多谷sdk:修改推荐－%@",message);
            if (success) {
                success(NO);
            }
        }
        
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        NSLog(@"多谷sdk:修改推荐异常 -%@", error);
        if (success) {
            success(NO);
        }
    }];
}


- (void)startGetStatictics:(NSString*)dateString back:(backSuccess)success
{
    NSMutableDictionary *parameters = [[NSMutableDictionary alloc] init];
    [parameters setObject:dateString forKey:@"currentDate"];
    
    [self GetStatictics:parameters back:success];
}

- (void)GetStatictics:(NSDictionary*)parameters back:(backSuccess)success
{
    NSString *url = [NSString stringWithFormat:@"%@/%@",ZY_HOST,ZY_URL_STATICTICS];
    
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    manager.responseSerializer = [AFHTTPResponseSerializer serializer];
    NSLog(@"多谷sdk:获取统计=>%@",url);
    
    [manager GET:url parameters:parameters progress:nil success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:responseObject options:NSJSONReadingMutableLeaves error:nil];
        
        NSLog(@"多谷sdk:获取统计<=%@",dic);
        NSString* code = dic[@"code"];
        if (code && code.intValue == 0) {
            [self.pushStatictics removeAllObjects];
            NSArray* dataList = dic[@"content"];
            for (id value in dataList) {
                id reslut = self.pushStatictics[value[@"gameId"]];
                if (!reslut) {
                    NSMutableArray *array = [[NSMutableArray alloc] init];
                    [array addObject:value];
                    [self.pushStatictics setObject:array forKey:value[@"gameId"]];
                }else{
                    NSMutableArray *array = reslut;
                    [array addObject:value];
                }
            }
            if (success) {
                success(YES);
            }
        }else{
            NSString* message = dic[@"msg"];
            NSLog(@"多谷sdk:获取统计－%@",message);
            if (success) {
                success(NO);
            }
        }
        
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        NSLog(@"多谷sdk:获取统计异常 -%@", error);
        if (success) {
            success(NO);
        }
    }];
}


@end
